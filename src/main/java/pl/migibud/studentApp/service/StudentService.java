package pl.migibud.studentApp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.migibud.studentApp.model.Student;

public interface StudentService {
    Student addStudent(Student student);
    Page<Student> listStudents(PageRequest pageRequest);
    Student getStudentById(Long studentId);
    boolean deleteStudentById(Long studentId);
}
