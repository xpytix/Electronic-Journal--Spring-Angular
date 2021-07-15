package com.example.CarRentalApi.school.dto;


import lombok.Data;

@Data

public class CreditDto {
    private Integer grade;
    private Boolean attempt;
    private CourseDto course;
}
