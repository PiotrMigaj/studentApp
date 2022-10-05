package pl.migibud.studentApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEnrolmentRequest {
    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
}
