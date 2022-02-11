package com.example.CarRentalApi.school.dto;

import com.example.CarRentalApi.school.dto.teacher.TeacherDtoRegister;
import com.example.CarRentalApi.school.payload.request.SignupRequest;
import lombok.Data;

@Data
public class UserTeacher {
    TeacherDtoRegister teacherDtoRegister;
    SignupRequest signupRequest;
}
