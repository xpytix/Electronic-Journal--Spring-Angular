package com.example.CarRentalApi.school.model.dto;


import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Teacher;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeacherDto {
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CourseDto> courses;

}
