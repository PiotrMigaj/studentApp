package pl.migibud.studentApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.migibud.studentApp.model.Status;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseRequest {
    @NotBlank
    private String name;
    private Status status;
}
