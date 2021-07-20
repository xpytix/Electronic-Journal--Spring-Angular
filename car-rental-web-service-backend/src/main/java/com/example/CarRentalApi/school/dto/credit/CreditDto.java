package com.example.CarRentalApi.school.dto.credit;

import com.example.CarRentalApi.school.dto.student.StudentDtoGet;
import com.example.CarRentalApi.school.dto.course.CourseDtoGet;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data

public class CreditDto {
    private Long id;
    private Integer grade;

    private Boolean attempt;
    private CourseDtoGet course;
    private StudentDtoGet student;

}
