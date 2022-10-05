package pl.migibud.studentApp.model.event;

import pl.migibud.studentApp.model.Status;

public class StudentInactiveStatusEvent {

    private Long studentId;

    public StudentInactiveStatusEvent(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }
}
