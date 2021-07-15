package com.example.CarRentalApi.school.dto;


import lombok.Data;

import java.util.List;

@Data

public class TeacherDto {
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CourseDto> courses;

}
