package com.example.CarRentalApi.school.dto.teacher;

import java.util.List;

import com.example.CarRentalApi.school.dto.course.CourseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data

public class TeacherDto {
    @Size(min = 5, message = "email should be at least 5 characters")
    @NotNull(message = "email cannot be an empty value")
    private String email;
    @Size(min = 5, message = "first name should be at least 5 characters")
    @NotNull(message = "first name cannot be an empty value")
    private String firstName;
    @Size(min = 5, message = "last name should be at least 5 characters")
    @NotNull(message = "last name cannot be an empty value")
    private String lastName;
    @Size(min = 5, message = "date of birth should be at least 5 characters")
    @NotNull(message = "date of birth cannot be an empty value")
    private String dateOfBirth;
    private List<CourseDto> courses;

}
