package pl.migibud.studentApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp
    LocalDateTime createdAt;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    Status studentStatus;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    Course course;
}
