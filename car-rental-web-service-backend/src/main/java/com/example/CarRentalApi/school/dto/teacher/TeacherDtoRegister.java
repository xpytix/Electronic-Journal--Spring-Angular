package com.example.CarRentalApi.school.dto.teacher;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TeacherDtoRegister {
    private Long id;
    @Size(min = 2, message = "email should be at least 5 characters")
    @NotNull(message = "email cannot be an empty value")
    private String email;
    @Size(min = 2, message = "first name should be at least 5 characters")
    @NotNull(message = "first name cannot be an empty value")
    private String firstName;
    @Size(min = 2, message = "last name should be at least 5 characters")
    @NotNull(message = "last name cannot be an empty value")
    private String lastName;
    @NotNull(message = "date of birth cannot be an empty value")
    private String dateOfBirth;
}