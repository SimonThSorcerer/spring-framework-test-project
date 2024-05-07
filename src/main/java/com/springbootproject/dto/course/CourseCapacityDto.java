package com.springbootproject.dto.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCapacityDto {
    @NotBlank(message = "Capacity: Must have a value, cannot be null.")
    @Min(value = 1, message = "Capacity: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "Capacity: Must have a value and be above 1 and below 2_000_000_000.")
    int courseDtoCapacity;
}
