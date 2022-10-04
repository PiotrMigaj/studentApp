package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.studentApp.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
