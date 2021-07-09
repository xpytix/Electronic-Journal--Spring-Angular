package com.example.CarRentalApi;

import com.example.CarRentalApi.school.model.*;
import com.example.CarRentalApi.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@SpringBootApplication
@RestController
public class CarRentalApiApplication  {

    @Autowired
    private static StudentRepository studentRepository;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApiApplication.class, args);
    }
//
//    @Override
//    public void run(String... arg) throws Exception {
//
//        Teacher Jozef = new Teacher("jozef@gmail.com", "Jozef", "Bomba", "13.01.1992");
//        Student marian = new Student("siemka@gmail.com", "Marian", "Kowalski", "13.01.1992");
//        Credit credit1 = new Credit(5, true);
//        Course programowanie = new Course("Programowanie");
//        Category It = new Category("it");
//        programowanie.setCategory(It);
//        programowanie.setTeacherid(Jozef.getId());
//        credit1.setStudent_id(marian.getId());
//        credit1.setCourse_id(programowanie.getId());
//
//        teacherRepository.save(Jozef);
//        studentRepository.save(marian);
//        courseRepository.save(programowanie);
//        categoryRepository.save(It);
//        creditRepository.save(credit1);
//
//    }

}
