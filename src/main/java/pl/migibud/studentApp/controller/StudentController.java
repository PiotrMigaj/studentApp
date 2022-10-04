package pl.migibud.studentApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.studentApp.model.Student;
import pl.migibud.studentApp.model.dto.CreateStudentRequest;
import pl.migibud.studentApp.model.dto.StudentDto;
import pl.migibud.studentApp.service.StudentService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
class StudentController {

    private final StudentService studentService;

    @GetMapping
    ResponseEntity<Page<StudentDto>> getListOfStudents(
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(studentService.listStudents(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    ResponseEntity<StudentDto> addStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        StudentDto studentDto = studentService.addStudent(createStudentRequest);
        return ResponseEntity.created(URI.create("/api/student/"+studentDto.getId())).body(studentDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
