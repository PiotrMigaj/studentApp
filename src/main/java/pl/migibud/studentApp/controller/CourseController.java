package pl.migibud.studentApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.studentApp.model.dto.CourseDto;
import pl.migibud.studentApp.model.dto.CourseDto2;
import pl.migibud.studentApp.model.dto.CreateCourseRequest;
import pl.migibud.studentApp.service.CourseService;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
class CourseController {

    private final CourseService courseService;

    @GetMapping
    ResponseEntity<Page<CourseDto>> getListOfCourses(
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false,defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(courseService.listCourses(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<CourseDto2> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    ResponseEntity<CourseDto> addCourse(@RequestBody @Valid CreateCourseRequest createCourseRequest){
        CourseDto courseDto = courseService.addCourse(createCourseRequest);
        return ResponseEntity.created(URI.create("/api/course/"+courseDto.getId())).body(courseDto);
    }
}
