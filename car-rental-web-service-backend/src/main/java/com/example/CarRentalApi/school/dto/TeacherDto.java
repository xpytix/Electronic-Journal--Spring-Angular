package com.example.CarRentalApi.school.dto;

import java.util.List;

import lombok.Data;


@Data

public class TeacherDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CourseDto> courses;

}
