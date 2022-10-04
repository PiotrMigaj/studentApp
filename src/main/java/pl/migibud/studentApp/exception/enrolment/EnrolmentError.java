package pl.migibud.studentApp.exception.enrolment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnrolmentError {

    ENROLMENT_NOT_FOUND("Enrolment does not exist");
    private final String message;
}
