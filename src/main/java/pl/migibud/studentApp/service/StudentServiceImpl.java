package pl.migibud.studentApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.migibud.studentApp.exception.student.StudentError;
import pl.migibud.studentApp.exception.student.StudentException;
import pl.migibud.studentApp.model.Status;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CreateStudentRequest;
import pl.migibud.studentApp.model.dto.StudentDto;
import pl.migibud.studentApp.model.mapper.StudentMapper;
import pl.migibud.studentApp.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto addStudent(CreateStudentRequest createStudentRequest) {
        Student student = studentMapper.mapCreateStudentRequestToStudent(createStudentRequest);
        student.setStatus(Status.ACTIVE);
        return studentMapper.mapStudentToStudentDto(studentRepository.save(student));
    }

    @Override
    public Page<StudentDto> listStudents(PageRequest pageRequest) {
        Page<Student> studentPage = studentRepository.findAll(pageRequest);
        List<StudentDto> collect = studentPage.getContent().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, studentPage.getPageable(), studentPage.getTotalElements());
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(studentMapper::mapStudentToStudentDto)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }
}
