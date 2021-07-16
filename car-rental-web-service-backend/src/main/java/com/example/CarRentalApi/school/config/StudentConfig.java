//package com.example.CarRentalApi.school.config;
//
//import com.example.CarRentalApi.school.model.Student;
//import com.example.CarRentalApi.school.repository.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerStudent(StudentRepository repository){
//        return args -> {
//                    Student marian = new Student(
//                             "siemka@gmail.com","Marian", "Kowalski",
//                            "13.01.1992"
//                    );
//            Student frank = new Student(
//                    "siemka@gmail.com","Frank", "Dab",
//                            "1.11.2009"
//                    );
//
//                    repository.saveAll(
//                            List.of(marian, frank)
//                    );
//        };
//    }
//}
