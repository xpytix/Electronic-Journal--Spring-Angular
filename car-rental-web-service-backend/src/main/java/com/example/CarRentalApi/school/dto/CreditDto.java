package com.example.CarRentalApi.school.dto;

import lombok.Data;


@Data

public class CreditDto {
    private Long id;
    private Integer grade;
    private Boolean attempt;
    private CourseSlimDto course;
    private StudentSlimDto student;

}
