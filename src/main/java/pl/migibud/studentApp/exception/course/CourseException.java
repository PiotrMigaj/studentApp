package pl.migibud.studentApp.exception.course;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.migibud.studentApp.exception.enrolment.EnrolmentError;

@Getter
@RequiredArgsConstructor
public class CourseException extends RuntimeException {

    private final CourseError courseError;
}
