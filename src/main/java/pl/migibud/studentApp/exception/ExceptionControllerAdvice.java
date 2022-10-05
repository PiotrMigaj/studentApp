package pl.migibud.studentApp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.migibud.studentApp.exception.course.CourseError;
import pl.migibud.studentApp.exception.course.CourseException;
import pl.migibud.studentApp.exception.enrolment.EnrolmentError;
import pl.migibud.studentApp.exception.enrolment.EnrolmentException;
import pl.migibud.studentApp.exception.student.StudentError;
import pl.migibud.studentApp.exception.student.StudentException;

@Slf4j
@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<ErrorMessage> handleStudentException(StudentException e) {
        HttpStatus httpStatus = null;
        if (StudentError.STUDENT_NOT_FOUND.equals(e.getStudentError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (StudentError.STUDENT_IS_INACTIVE.equals(e.getStudentError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorMessage(e.getStudentError().getMessage()));
    }

    @ExceptionHandler(value = CourseException.class)
    public ResponseEntity<ErrorMessage> handleCourseException(CourseException e) {
        HttpStatus httpStatus = null;
        if (CourseError.COURSE_NOT_FOUND.equals(e.getCourseError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (CourseError.COURSE_IS_INACTIVE.equals(e.getCourseError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (CourseError.PARTICIPANTS_LIMIT_EXCEEDED.equals(e.getCourseError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorMessage(e.getCourseError().getMessage()));
    }

    @ExceptionHandler(value = EnrolmentException.class)
    public ResponseEntity<ErrorMessage> handleEnrolmentException(EnrolmentException e) {
        HttpStatus httpStatus = null;
        if (EnrolmentError.ENROLMENT_NOT_FOUND.equals(e.getEnrolmentError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (EnrolmentError.STUDENT_ALREADY_ENROLLED_IN_COURSE.equals(e.getEnrolmentError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        if (EnrolmentError.STUDENT_ALREADY_ENROLLED_IN_THREE_ACTIVE_COURSES.equals(e.getEnrolmentError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        if (EnrolmentError.STUDENT_IS_NOT_ENROLLED_IN_COURSE.equals(e.getEnrolmentError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorMessage(e.getEnrolmentError().getMessage()));
    }
}
