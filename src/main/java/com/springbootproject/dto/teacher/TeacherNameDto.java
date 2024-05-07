package com.springbootproject.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherNameDto {
    @NotBlank(message = "Name: Must have a value, cannot be blank.")
    @Size(max = 1_000, message = "Name: Over the character limit of 1_000.")
    private String teacherDtoName;
}

