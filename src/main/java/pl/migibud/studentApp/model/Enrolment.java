package pl.migibud.studentApp.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp
    LocalDateTime createdAt;
    @Enumerated(value = EnumType.STRING)

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    Student student;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    Course course;
}
