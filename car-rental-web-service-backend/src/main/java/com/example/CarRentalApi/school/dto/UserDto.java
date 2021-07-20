package com.example.CarRentalApi.school.dto;

import com.example.CarRentalApi.school.model.Student;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String role;
}
