package pl.migibud.studentApp.exception.enrolment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrolmentError {

    ENROLMENT_NOT_FOUND("Enrolment does not exist"),
    STUDENT_ALREADY_ENROLLED_IN_COURSE("Student already enrolled in course"),
    STUDENT_IS_NOT_ENROLLED_IN_COURSE("Student is not enrolled in course"),
    STUDENT_ALREADY_ENROLLED_IN_THREE_ACTIVE_COURSES("Student already enrolled in three active courses");
    private final String message;
}
