package com.example.CarRentalApi.school.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentDto {
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private List<CreditDto> credits;

}
