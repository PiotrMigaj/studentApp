package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.studentApp.model.Enrolment;

public interface EnrolmentRepository extends JpaRepository<Enrolment,Long> {
}
