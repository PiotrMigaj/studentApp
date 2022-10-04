package pl.migibud.studentApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.migibud.studentApp.exception.student.StudentError;
import pl.migibud.studentApp.exception.student.StudentException;
import pl.migibud.studentApp.model.Status;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        student.setStatus(Status.ACTIVE);
        return studentRepository.save(student);
    }

    @Override
    public Page<Student> listStudents(PageRequest pageRequest) {
        Page<Student> studentPage = studentRepository.findAll(pageRequest);
        List<Student> content = studentPage.getContent();
        return new PageImpl<>(content,studentPage.getPageable(),studentPage.getTotalElements());
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(()->new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public boolean deleteStudentById(Long studentId) {
        return false;
    }
}
