package pl.migibud.studentApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import pl.migibud.studentApp.exception.course.CourseError;
import pl.migibud.studentApp.exception.course.CourseException;
import pl.migibud.studentApp.exception.enrolment.EnrolmentError;
import pl.migibud.studentApp.exception.enrolment.EnrolmentException;
import pl.migibud.studentApp.exception.student.StudentError;
import pl.migibud.studentApp.exception.student.StudentException;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.Status;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CreateEnrolmentRequest;
import pl.migibud.studentApp.model.event.StudentInactiveStatusEvent;
import pl.migibud.studentApp.repository.CourseRepository;
import pl.migibud.studentApp.repository.EnrolmentRepository;
import pl.migibud.studentApp.repository.StudentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class EnrolmentServiceImpl implements EnrolmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrolmentRepository enrolmentRepository;

    @Override
    @Transactional
    public Enrolment enrollStudentToCourse(CreateEnrolmentRequest createEnrolmentRequest) {

        Long studentId = createEnrolmentRequest.getStudentId();
        Long courseId = createEnrolmentRequest.getCourseId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(CourseError.COURSE_NOT_FOUND));

        isStudentStatusActive(student);
        isCourseStatusActive(course);
        ifCourseParticipantsLimitNotExceeded(course);
        isStudentEnrolledInCourse(studentId,courseId);
        isStudentAlreadyEnrolledOnThreeActiveCourses(studentId);

        Enrolment enrolment = Enrolment.builder()
                .student(student)
                .course(course)
                .studentStatus(Status.ACTIVE)
                .build();

        return enrolmentRepository.save(enrolment);
    }



    @Override
    public Page<Enrolment> listEnrolments(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Enrolment getEnrolmentById(Long enrolmentId) {
        return null;
    }

    @Override
    public void deleteEnrolmentById(Long enrolmentId) {

    }

    @Override
    @Transactional
    public void removeStudentFromCourse(CreateEnrolmentRequest createEnrolmentRequest) {
        Long studentId = createEnrolmentRequest.getStudentId();
        Long courseId = createEnrolmentRequest.getCourseId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(CourseError.COURSE_NOT_FOUND));

        isStudentStatusActive(student);
        isCourseStatusActive(course);
        isStudentNotEnrolledInCourse(studentId,courseId);

        Enrolment enrolment = enrolmentRepository.findByStudent_IdAndCourse_Id(studentId, courseId)
                .orElseThrow(() -> new EnrolmentException(EnrolmentError.ENROLMENT_NOT_FOUND));

        enrolment.setStudentStatus(Status.INACTIVE);

        enrolmentRepository.save(enrolment);
        Long participantsNumber = course.getParticipantsNumber();
        course.setParticipantsNumber(--participantsNumber);
        courseRepository.save(course);
    }

    @Async //TODO - Understand why @Async and @TransactionalEventListener
    @EventListener
//    @Transactional
    void handleStudentInactiveStatusEvent(StudentInactiveStatusEvent event){
        log.info("Handling event - change status of student to INACTIVE - studentId: "+event.getStudentId());

        List<Enrolment> enrolments = enrolmentRepository.findByStudent_IdAndStudentStatus(event.getStudentId(), Status.ACTIVE);
        if (enrolments.size()>0){
            enrolments
                    .forEach(enrolment -> {
                        enrolment.setStudentStatus(Status.INACTIVE);
                        Course course = enrolment.getCourse();
                        Long participantsNumber = course.getParticipantsNumber();
                        course.setParticipantsNumber(--participantsNumber);
                        courseRepository.save(course);
                        enrolmentRepository.save(enrolment);
                    });
        }

        //TODO - check out transactions
//        System.out.println(5/0);
    }

    private boolean isStudentStatusActive(Student student){
        if (Status.INACTIVE.equals(student.getStatus())){
            throw new StudentException(StudentError.STUDENT_IS_INACTIVE);
        }
        return true;
    }

    private boolean isCourseStatusActive(Course course){
        if (Status.INACTIVE.equals(course.getStatus())){
            throw new CourseException(CourseError.COURSE_IS_INACTIVE);
        }
        return true;
    }

    private boolean ifCourseParticipantsLimitNotExceeded(Course course){
        Long participantsLimit = course.getParticipantsLimit();
        Long participantsNumber = course.getParticipantsNumber();
        if (participantsLimit.equals(participantsNumber)){
            throw new CourseException(CourseError.PARTICIPANTS_LIMIT_EXCEEDED);
        }
        course.setParticipantsNumber(++participantsNumber);
        courseRepository.save(course);
        return true;
    }

    private boolean isStudentEnrolledInCourse(Long studentId,Long courseId){
        long count = enrolmentRepository.countByStudent_IdAndCourse_IdAndStudentStatus(studentId, courseId,Status.ACTIVE);
        if (count!=0){
            throw new EnrolmentException(EnrolmentError.STUDENT_ALREADY_ENROLLED_IN_COURSE);
        }
        return false;
    }

    private boolean isStudentNotEnrolledInCourse(Long studentId,Long courseId){
        long count = enrolmentRepository.countByStudent_IdAndCourse_IdAndStudentStatus(studentId, courseId,Status.ACTIVE);
        if (count==0){
            throw new EnrolmentException(EnrolmentError.STUDENT_IS_NOT_ENROLLED_IN_COURSE);
        }
        return false;
    }

    private boolean isStudentAlreadyEnrolledOnThreeActiveCourses(Long studentId){
        List<Enrolment> enrolments = enrolmentRepository.findByStudent_IdAndStudentStatus(studentId, Status.ACTIVE);
        long count = enrolments.stream()
                .filter(enrolment -> Status.ACTIVE.equals(enrolment.getCourse().getStatus()))
                .count();
        if (count==3){
            throw new EnrolmentException(EnrolmentError.STUDENT_ALREADY_ENROLLED_IN_THREE_ACTIVE_COURSES);
        }
        return false;
    }
}
