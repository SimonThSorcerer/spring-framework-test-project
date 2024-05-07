package com.springbootproject.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseIdDto {
    @Min(value = 1, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    int CourseDtoId;
}
