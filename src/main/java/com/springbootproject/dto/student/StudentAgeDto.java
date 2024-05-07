package com.springbootproject.dto.student;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAgeDto {
    @Max(value = 2_000_000_000, message = "Age: Exceeded the age limit of 2_000_000_000.")
    @Min(value = 1, message = "Age: Must have a value and be above 1.")
    private int StudentDtoAge;
}




