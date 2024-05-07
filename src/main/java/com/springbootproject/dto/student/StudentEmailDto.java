package com.springbootproject.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEmailDto {
    @Email(message = "Email format is invalid. Please provide a valid email address.")
    @Size(max = 1_000, message = "Email: Over the character limit of 1_000.")
    @Pattern(regexp = "^[^@.]+@[^@.]+\\.[^@.]+$", message = "Email: Format is invalid. Please provide a valid email address.")
    private String StudentDtoEmail;
}


