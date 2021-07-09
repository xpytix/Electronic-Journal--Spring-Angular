package com.example.CarRentalApi.school.config;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.repository.CategoryRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@EnableSwagger2
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository repository) {
        return args -> {
            Category It = new Category(
                    "it"
            );
            Category Jezyki = new Category(
                    "jezyki"
            );

            repository.saveAll(
                    List.of(It, Jezyki)
            );
        };
    }
}