package com.example.CarRentalApi.school.dto.credit;

import com.example.CarRentalApi.school.dto.student.StudentDtoGet;
import lombok.Data;


@Data
public class CreditDtoGet {
    private Long id;
    private Integer grade;
    private Boolean attempt;
    private StudentDtoGet student;
}
