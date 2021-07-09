//package com.example.CarRentalApi.school.config;
//
//import com.example.CarRentalApi.school.model.Category;
//import com.example.CarRentalApi.school.model.Course;
//import com.example.CarRentalApi.school.model.Credit;
//import com.example.CarRentalApi.school.repository.CourseRepository;
//import com.example.CarRentalApi.school.repository.CreditRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class CourseConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerCourse(CourseRepository repository) {
//        return args -> {
//            Course programowanie = new Course(
//                     "Programowanie"
//            );
//            Course bazy_danych = new Course(
//                    "bazy_danych"
//            );
//
//            repository.saveAll(
//                    List.of(programowanie, bazy_danych)
//            );
//        };
//    }
//};