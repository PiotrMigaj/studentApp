package pl.migibud.studentApp.exception.course;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseError {

    COURSE_NOT_FOUND("Course does not exist"),
    COURSE_IS_INACTIVE("Course is inactive"),
    PARTICIPANTS_LIMIT_EXCEEDED("Participants limit exceeded");
    private final String message;
}
