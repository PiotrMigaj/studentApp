package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.dto.CreateEnrolmentRequest;

public interface EnrolmentService {
    Enrolment enrollStudentToCourse(CreateEnrolmentRequest createEnrolmentRequest);
    Page<Enrolment> listEnrolments(PageRequest pageRequest);
    Enrolment getEnrolmentById(Long enrolmentId);
    void deleteEnrolmentById(Long enrolmentId);
    void removeStudentFromCourse(CreateEnrolmentRequest createEnrolmentRequest);
}
