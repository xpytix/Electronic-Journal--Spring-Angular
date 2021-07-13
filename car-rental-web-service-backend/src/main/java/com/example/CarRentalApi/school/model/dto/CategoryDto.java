package com.example.CarRentalApi.school.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CategoryDto {
    private String name;
    private List<CourseDto> courses;

}
