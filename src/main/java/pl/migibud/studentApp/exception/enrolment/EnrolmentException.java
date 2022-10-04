package pl.migibud.studentApp.exception.enrolment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.migibud.studentApp.exception.student.StudentError;

@Getter
@RequiredArgsConstructor
public class EnrolmentException extends RuntimeException {

    private final EnrolmentError enrolmentError;
}
