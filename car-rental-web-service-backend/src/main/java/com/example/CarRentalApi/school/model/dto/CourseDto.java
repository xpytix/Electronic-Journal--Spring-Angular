package com.example.CarRentalApi.school.model.dto;

import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.model.Teacher;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
    private String name;
    private TeacherDto teacher;
    private CreditDto credit;
}
