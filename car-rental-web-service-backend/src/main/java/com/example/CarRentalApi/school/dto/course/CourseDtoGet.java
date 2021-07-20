package com.example.CarRentalApi.school.dto.course;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class CourseDtoGet {
    private Long id;
    @Size(min = 2, message = "Name should be at least 2 characters")
    @NotNull(message = "Name cannot be an empty value")
    private String name;
}
