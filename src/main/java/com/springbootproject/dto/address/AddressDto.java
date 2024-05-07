package com.springbootproject.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    @NotNull
    int id;

    @NotBlank
    String lane1;

    String lane2;

    @NotBlank
    String state;

    @NotBlank
    String zip;
}
