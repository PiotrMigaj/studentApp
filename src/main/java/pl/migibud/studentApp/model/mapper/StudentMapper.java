package pl.migibud.studentApp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CreateStudentRequest;
import pl.migibud.studentApp.model.dto.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings(value = {
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName")
    })
    Student mapCreateStudentRequestToStudent(CreateStudentRequest createStudentRequest);

    @Mappings(value = {
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName")
    })
    StudentDto mapStudentToStudentDto(Student student);
}
