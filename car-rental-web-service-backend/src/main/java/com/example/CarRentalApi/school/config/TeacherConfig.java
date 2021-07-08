package com.example.CarRentalApi.school.config;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.StudentRepository;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TeacherConfig {
    @Bean
    CommandLineRunner commandLineRunnerTeacher(TeacherRepository repository){
        return args -> {
            Teacher Jozef = new Teacher(
                    1L,"jozef@gmail.com","Jozef", "Bomba",
                    "13.01.1992"
            );
            Teacher Andrzej = new Teacher(
                    2L,"andrzeja@gmail.com","Andrzej", "Nuda",
                    "1.11.2009"
            );

            repository.saveAll(
                    List.of(Jozef, Andrzej)
            );
        };
    }
}
