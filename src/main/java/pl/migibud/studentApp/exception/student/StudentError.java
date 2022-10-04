package pl.migibud.studentApp.exception.student;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentError {

    STUDENT_NOT_FOUND("Student does not exist");
    private final String message;
}
