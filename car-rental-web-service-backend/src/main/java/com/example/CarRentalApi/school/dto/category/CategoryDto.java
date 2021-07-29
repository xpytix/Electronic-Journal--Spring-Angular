package com.example.CarRentalApi.school.dto.category;

import java.util.List;

import com.example.CarRentalApi.school.dto.course.CourseDto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;


@Getter
@Setter
public class CategoryDto {
    private Long id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    @NotNull(message = "Name cannot be an empty value")
    private String name;
    private List<CourseDto> courses;
}
