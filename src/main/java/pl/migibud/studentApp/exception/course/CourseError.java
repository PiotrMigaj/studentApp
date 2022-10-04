package pl.migibud.studentApp.exception.course;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseError {

    COURSE_NOT_FOUND("Course does not exist");
    private final String message;
}
