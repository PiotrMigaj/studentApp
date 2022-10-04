package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.Enrolment;

public interface CourseService {
    Course addCourse(Course course);
    Page<Course> listCourses(PageRequest pageRequest);
    Course getCourseById(Course courseId);
    boolean deleteCourseById(Course courseId);
}
