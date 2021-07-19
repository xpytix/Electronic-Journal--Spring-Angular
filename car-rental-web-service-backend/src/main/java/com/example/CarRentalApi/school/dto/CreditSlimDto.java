package com.example.CarRentalApi.school.dto;

import lombok.Data;


@Data
public class CreditSlimDto {
    private Long id;
    private Integer grade;
    private Boolean attempt;
    private StudentSlimDto student;
}
