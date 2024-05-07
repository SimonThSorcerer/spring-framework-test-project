package com.springbootproject.dto.teacher;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherIdDto {
    @Min(value = 1, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    private int teacherDtoId;
}

