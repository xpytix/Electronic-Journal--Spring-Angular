package com.example.CarRentalApi.school.dto.student;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentDtoPut {
    private Long id;
}
