package com.example.CarRentalApi.school.model.dto;

import com.example.CarRentalApi.school.model.Course;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditDto {
    private Integer grade;
    private Boolean attempt;
    private CourseDto course;
}
