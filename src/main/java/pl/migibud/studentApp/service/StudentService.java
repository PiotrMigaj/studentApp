package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CreateStudentRequest;
import pl.migibud.studentApp.model.dto.StudentDto;

public interface StudentService {
    StudentDto addStudent(CreateStudentRequest createStudentRequest);
    Page<StudentDto> listStudents(PageRequest pageRequest);
    StudentDto getStudentById(Long studentId);
    void deleteStudentById(Long studentId);
}
