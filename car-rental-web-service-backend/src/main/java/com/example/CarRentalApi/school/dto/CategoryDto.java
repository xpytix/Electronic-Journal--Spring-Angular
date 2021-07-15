package com.example.CarRentalApi.school.dto;


import lombok.Data;

import java.util.List;
@Data
public class CategoryDto {
    private String name;
    private List<CourseDto> courses;

}
