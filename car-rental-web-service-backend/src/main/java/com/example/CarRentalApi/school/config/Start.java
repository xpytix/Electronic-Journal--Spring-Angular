package com.example.CarRentalApi.school.config;

import com.example.CarRentalApi.school.model.User;
import com.example.CarRentalApi.school.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Start {

    public Start(UserRepository userRepository, PasswordEncoder passwordEncoder){
        User userTeacher = new User();
        userTeacher.setUsername("teacher");
        userTeacher.setPassword(passwordEncoder.encode("admin"));
        userTeacher.setRole("ROLE_ADMIN");
        userRepository.save(userTeacher);
    }
}
