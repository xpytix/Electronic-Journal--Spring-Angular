package com.example.CarRentalApi.school.dto;

import lombok.Data;


@Data
public class CourseDto {
    private Long id;
    private String name;
    private TeacherSlimDto teacher;
    private CreditSlimDto credit;
}
