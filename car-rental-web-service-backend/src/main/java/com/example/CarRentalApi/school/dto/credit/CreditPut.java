package com.example.CarRentalApi.school.dto.credit;

import com.example.CarRentalApi.school.dto.course.CourseDtoGet;
import com.example.CarRentalApi.school.dto.course.CourseDtoPut;
import com.example.CarRentalApi.school.dto.student.StudentDtoGet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditPut {
    private Long id;
    private Integer grade;
    private Boolean attempt;
    private CourseDtoPut course;
}
