package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.Status;

import java.util.List;
import java.util.Optional;

public interface EnrolmentRepository extends JpaRepository<Enrolment,Long> {
    long countByStudent_IdAndCourse_IdAndStudentStatus(Long studentId, Long courseId, Status studentStatus);
    List<Enrolment> findByStudent_IdAndStudentStatus(Long studentId, Status studentStatus);

    Optional<Enrolment> findByStudent_IdAndCourse_Id(Long studentId, Long courseId);
}
