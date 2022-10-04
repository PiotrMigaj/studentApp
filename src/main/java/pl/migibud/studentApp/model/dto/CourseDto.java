package pl.migibud.studentApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.studentApp.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Long id;
    private String name;
    private Status status;
    private Long participantsLimit;
    private Long participantsNumber;
}
