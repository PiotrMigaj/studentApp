package pl.migibud.studentApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.studentApp.model.Enrolment;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;
import pl.migibud.studentApp.model.dto.CreateEnrolmentRequest;
import pl.migibud.studentApp.repository.EnrolmentRepository;
import pl.migibud.studentApp.service.EnrolmentService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/enrolment")
@RequiredArgsConstructor
class EnrolmentController {

    private final EnrolmentService enrolmentService;

    @PostMapping
    ResponseEntity<Enrolment> enrollStudentInCourse(@RequestBody @Valid CreateEnrolmentRequest createEnrolmentRequest){
        Enrolment enrolment = enrolmentService.enrollStudentToCourse(createEnrolmentRequest);
        return ResponseEntity.created(URI.create("/")).body(enrolment);
    }

    @DeleteMapping
    ResponseEntity<?> removeStudentFromCourse(@RequestBody @Valid CreateEnrolmentRequest createEnrolmentRequest){
        enrolmentService.removeStudentFromCourse(createEnrolmentRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
