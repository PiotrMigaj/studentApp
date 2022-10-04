package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.studentApp.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
