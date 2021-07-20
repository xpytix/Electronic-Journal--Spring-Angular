package com.example.CarRentalApi.school.dto;

import java.util.List;

import com.example.CarRentalApi.school.model.User;
import lombok.Data;


@Data

public class StudentDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CreditDto> credits;
}
