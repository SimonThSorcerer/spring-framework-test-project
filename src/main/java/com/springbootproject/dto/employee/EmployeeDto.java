package com.springbootproject.dto.employee;

import com.springbootproject.dto.address.AddressDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotNull
    private int id;

    @NotBlank(message = "Please type in your first name here.")
    private String firstName;

    @NotBlank(message = "Please type in your last name here.")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Please type in your blood group.0")
    private String bloodGroup;

    private AddressDto addressDto;
}
