package pl.migibud.studentApp.exception.student;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentException extends RuntimeException {

    private final StudentError studentError;
}
