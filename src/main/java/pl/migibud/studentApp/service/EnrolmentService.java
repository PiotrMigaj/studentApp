package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.Student;

public interface EnrolmentService {
    Enrolment addEnrolment(Enrolment enrolment);
    Page<Enrolment> listEnrolments(PageRequest pageRequest);
    Enrolment getEnrolmentById(Long enrolmentId);
    void deleteEnrolmentById(Long enrolmentId);
}
