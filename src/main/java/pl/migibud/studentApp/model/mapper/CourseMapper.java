package pl.migibud.studentApp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.studentApp.model.Course;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;
import pl.migibud.studentApp.model.dto.CreateStudentRequest;
import pl.migibud.studentApp.model.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mappings(value = {
            @Mapping(target = "firstName", source = "name"),
            @Mapping(target = "lastName", source = "status")
    })
    Course mapCreateCourseRequestToCourse(CreateCourseRequest createCourseRequest);

    @Mappings(value = {
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "status", source = "status")
    })
    CourseDto mapCourseToCourseDto(Course course);
}
