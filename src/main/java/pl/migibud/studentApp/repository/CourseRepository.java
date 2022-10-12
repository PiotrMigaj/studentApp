package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.studentApp.model.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    <T> T findById(Long id, Class<T> type);
}
