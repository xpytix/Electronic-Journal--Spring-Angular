package com.example.CarRentalApi.school.dto;

import java.util.List;

import lombok.Data;


@Data
public class CategoryDto {
    private Long id;
    private String name;
    private List<CourseDto> courses;
}
