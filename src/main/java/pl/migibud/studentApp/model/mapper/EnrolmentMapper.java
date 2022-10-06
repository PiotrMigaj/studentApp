package pl.migibud.studentApp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.dto.EnrolmentDto;

@Mapper(componentModel = "spring")
public interface EnrolmentMapper {


    @Mappings(value = {
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "studentStatus", source = "studentStatus"),
            @Mapping(target = "studentDto", source = "student"),
            @Mapping(target = "courseDto", source = "course")
    })
    EnrolmentDto mapEnrolmentToEnrolmentDto(Enrolment enrolment);
}
