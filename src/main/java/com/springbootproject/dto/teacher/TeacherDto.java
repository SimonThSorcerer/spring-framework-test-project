package com.springbootproject.dto.teacher;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {
    @Min(value = 1, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    private int teacherDtoId;

    @NotBlank(message = "Name: Name must have a value, cannot be blank.")
    @Size(max = 1_000, message = "Name: Over the character limit of 1_000.")
    private String teacherDtoName;

    @Email(message = "Email: Format is invalid. Please provide a valid email address.")
    @Size(max = 1_000, message = "Email: Over the character limit of 1_000.")
    @Pattern(regexp = "^[^@.]+@[^@.]+\\.[^@.]+$", message = "Email: Format is invalid. Please provide a valid email address.")
    private String teacherDtoEmail;
}

