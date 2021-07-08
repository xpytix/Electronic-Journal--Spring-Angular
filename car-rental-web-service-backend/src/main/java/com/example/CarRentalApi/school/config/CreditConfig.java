//package com.example.CarRentalApi.school.config;
//
//import com.example.CarRentalApi.school.model.Credit;
//import com.example.CarRentalApi.school.model.Student;
//import com.example.CarRentalApi.school.repository.CreditRepository;
//import com.example.CarRentalApi.school.repository.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//
//@Configuration
//public class CreditConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerCredit(CreditRepository repository) {
//        return args -> {
//            Credit obj1 = new Credit(
//                    5, true, 1L,
//                    1L
//            );
//            Credit obj2 = new Credit(
//                    2, false, 2L,
//                    1L
//            );
//
//            repository.saveAll(
//                    List.of(obj1, obj2)
//            );
//        };
//    }
//};