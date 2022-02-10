package com.example.CarRentalApi.school.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private Long id;
    @Size(min = 5, message = "username should be at least 5 characters")
    @NotNull(message = "username cannot be an empty value")
    private String username;
    @Size(min = 5, message = "Password should be at least 5 characters")
    @NotNull(message = "Password cannot be an empty value")
    private String password;

    private String role;

    public UserDto(String username, String password) {
        username = username;
        password = password;
    }
}
