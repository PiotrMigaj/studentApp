package pl.migibud.studentApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CourseDto2;

public interface CourseQueryRepository extends Repository<Course,Long> {
    CourseDto2 findById(Long id);
}
