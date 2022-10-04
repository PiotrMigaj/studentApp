package pl.migibud.studentApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.migibud.studentApp.exception.course.CourseError;
import pl.migibud.studentApp.exception.course.CourseException;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.InitialCourseData;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;
import pl.migibud.studentApp.model.mapper.CourseMapper;
import pl.migibud.studentApp.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final InitialCourseData initialCourseData;

    @Override
    public CourseDto addCourse(CreateCourseRequest createCourseRequest) {
        Course course = courseMapper.mapCreateCourseRequestToCourse(createCourseRequest);
        course.setParticipantsLimit(initialCourseData.getLimit());
        return courseMapper.mapCourseToCourseDto(courseRepository.save(course));
    }

    @Override
    public Page<CourseDto> listCourses(PageRequest pageRequest) {
        Page<Course> coursePage = courseRepository.findAll(pageRequest);
        List<CourseDto> collect = coursePage.getContent().stream()
                .map(courseMapper::mapCourseToCourseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect,coursePage.getPageable(),coursePage.getTotalElements());
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .map(courseMapper::mapCourseToCourseDto)
                .orElseThrow(()->new CourseException(CourseError.COURSE_NOT_FOUND));
    }

    @Override
    public void deleteCourseById(Long courseId) {
        //TODO - consider business logic - if removal of course is required
    }
}
