package com.example.CarRentalApi.school.dto;

import com.example.CarRentalApi.school.dto.student.StudentDtoRegister;
import com.example.CarRentalApi.school.payload.request.SignupRequest;
import lombok.Data;

@Data
public class UserStudent {
    StudentDtoRegister studentDtoRegister;
    SignupRequest signupRequest;
}
