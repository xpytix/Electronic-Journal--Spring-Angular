//package com.example.CarRentalApi.school.config;
//
//import com.example.CarRentalApi.school.model.Category;
//import com.example.CarRentalApi.school.model.Course;
//import com.example.CarRentalApi.school.repository.CategoryRepository;
//import com.example.CarRentalApi.school.repository.CourseRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import javax.persistence.PersistenceContext;
//
//@Configuration
//@EnableSwagger2
//@PersistenceContext
//public class CourseConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerCourse(CourseRepository repository, CategoryRepository crepository) {
//        return args -> {
//            Category category = new Category("IT");
//            Course programowanie = new Course(
//                    "Programowanie"
//            );
//            category.addCourse(programowanie);
//            Course bazy_danych = new Course(
//                    "bazy_danych"
//            );
//            category.addCourse(bazy_danych);
//            crepository.save(category);
//        };
//    }
//};
