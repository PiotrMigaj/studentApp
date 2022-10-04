package pl.migibud.studentApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;
import pl.migibud.studentApp.repository.CourseRepository;

@Slf4j
@Service
@RequiredArgsConstructor
class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDto addCourse(CreateCourseRequest createCourseRequest) {
        return null;
    }

    @Override
    public Page<CourseDto> listCourses(PageRequest pageRequest) {
        return null;
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return null;
    }

    @Override
    public void deleteCourseById(Long courseId) {

    }
}
