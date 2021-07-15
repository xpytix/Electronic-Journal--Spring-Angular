package com.example.CarRentalApi.school.dto;


import lombok.Data;

import java.util.List;

@Data

public class StudentDto {
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CreditDto> credits;
}
