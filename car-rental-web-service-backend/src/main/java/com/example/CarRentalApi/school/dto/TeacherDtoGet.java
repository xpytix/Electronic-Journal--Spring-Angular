package com.example.CarRentalApi.school.dto;

import lombok.Data;


@Data
public class TeacherDtoGet {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
