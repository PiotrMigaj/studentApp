package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CourseDto2;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;

public interface CourseService {
    CourseDto addCourse(CreateCourseRequest createCourseRequest);
    Page<CourseDto> listCourses(PageRequest pageRequest);
    CourseDto2 getCourseById(Long courseId);
    void deleteCourseById(Long courseId);
}
