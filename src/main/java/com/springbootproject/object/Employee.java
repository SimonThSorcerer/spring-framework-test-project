package com.springbootproject.object;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "employee")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @Pattern(regexp = "^[^@.]+@[^@.]+\\.[^@.]+$", message = "Email: Format is invalid. Please provide a valid email address.")
    private String email;

    @Column(name = "blood_group")
    String bloodGroup;
}
