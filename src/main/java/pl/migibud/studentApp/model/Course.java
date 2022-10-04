package pl.migibud.studentApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String name;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    Status status;
    @NotNull
    Long participantsLimit;
    @Min(0)
    Long participantsNumber;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "course")
    Set<Enrolment> enrolments;

    @PrePersist
    void prePersist() {
        participantsLimit = InitialCourseData.LIMIT;
    }

    private void setParticipantsLimit(Long participantsLimit) {
        this.participantsLimit = participantsLimit;
    }
}
