package com.example.CarRentalApi.school.dto.student;

import com.example.CarRentalApi.school.dto.user.UserDto;
import com.example.CarRentalApi.school.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentDtoRegister {
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