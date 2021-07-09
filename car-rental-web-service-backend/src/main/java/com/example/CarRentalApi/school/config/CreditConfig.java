//package com.example.CarRentalApi.school.config;
//
//import com.example.CarRentalApi.school.model.Credit;
//import com.example.CarRentalApi.school.model.Student;
//import com.example.CarRentalApi.school.repository.CreditRepository;
//import com.example.CarRentalApi.school.repository.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.List;
//
//
//@Configuration
//@EnableSwagger2
//public class CreditConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerCredit(CreditRepository repository) {
//        return args -> {
//            Credit obj1 = new Credit(
//                    5, true
//            );
//            Credit obj2 = new Credit(
//                    2, false
//            );
//
//            repository.saveAll(
//                    List.of(obj1, obj2)
//            );
//        };
//    }
//};