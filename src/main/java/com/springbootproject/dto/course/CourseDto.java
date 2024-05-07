package com.springbootproject.dto.course;

import com.springbootproject.object.Student;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    @NotBlank(message = "Capacity: Must have a value, cannot be null.")
    @Min(value = 1, message = "Capacity: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "Capacity: Must have a value and be above 1 and below 2_000_000_000.")
    int courseDtoCapacity;

    @Min(value = 1, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    @Max(value = 2_000_000_000, message = "ID: Must have a value and be above 1 and below 2_000_000_000.")
    private int courseDtoid;

    @NotBlank(message = "Name: Name must have a value, cannot be blank.")
    @Size(max = 1_000, message = "Name: Over the character limit of 1_000.")
    private String courseDtoname;

    @NotNull
    private List<Student> studentList;
}