package com.example.CarRentalApi.school.dto;

import lombok.Data;


@Data
public class CourseDto {
    private Long id;
    private String name;
    private TeacherDtoGet teacher;
    private CreditDtoGet credit;
}
