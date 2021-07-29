package com.example.CarRentalApi;

import com.example.CarRentalApi.school.model.*;
import com.example.CarRentalApi.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@SpringBootApplication
@EnableSwagger2
public class CarRentalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(CategoryRepository categoryRepository, CourseRepository courseRepository,
            CreditRepository creditRepository, StudentRepository studentRepository,
            TeacherRepository teacherRepository, UserRepository userRepository,PasswordEncoder passwordEncoder) {
        return args -> {
            User user1 = new User("user1", "ROLE_ADMIN");
            user1.setPassword(passwordEncoder.encode("admin"));
            User user2 = new User("user2", "ROLE_ADMIN");
            user2.setPassword(passwordEncoder.encode("admin"));

            Teacher teacherJozef = new Teacher("jozef@gmail.com", "Jozef", "Bomba", "13.01.1992");
            Teacher teacherAndrzej = new Teacher("andrzeja@gmail.com", "Andrzej", "Nuda", "1.11.2009");

            teacherJozef.setUser(user1);

            Course programowanie = new Course("Programowanie");

            Course bazy_danych = new Course("bazy_danych");

            Student studentDominik = new Student("dominik@gmail.com", "Dominik","Kowalski", "13.01.1992");
            Student studentSzymon = new Student("szymon@gmail.com", "Szymon","Kowalski", "13.01.1992");

            studentDominik.setUser(user2);

            Credit credit1 = new Credit(5, true);
            Credit credit2 = new Credit(5, true);
            Credit credit3 = new Credit(5, true);
            Category categoryFrontEnd = new Category("FrontEnd");
            Category categoryBackEnd = new Category("BackEnd");

            studentRepository.save(studentDominik);
            studentRepository.save(studentSzymon);

            studentDominik.addCredit(credit1);
            studentDominik.addCredit(credit2);
            studentDominik.addCredit(credit3);
            studentSzymon.addCredit(credit3);

            teacherRepository.save(teacherAndrzej);
            teacherRepository.save(teacherJozef);
            teacherJozef.addCourse(programowanie);
            teacherJozef.addCourse(bazy_danych);

            categoryRepository.save(categoryBackEnd);
            categoryRepository.save(categoryFrontEnd);

            categoryBackEnd.addCourse(programowanie);
            categoryFrontEnd.addCourse(bazy_danych);

            courseRepository.save(programowanie);
            programowanie.addCredit(credit1);

            creditRepository.save(credit1);
            credit1.setCourse(programowanie);




//
//            credit1.setStudent(studentDominik);
//            credit2.setStudent(studentDominik);
//            credit3.setStudent(studentSzymon);
//
//
//
//            credit1.setCourse(programowanie);
//            credit1.setStudent(studentDominik);
            creditRepository.save(credit1);
//            programowanie.getCredits().add(credit1);
//            programowanie.setTeacher(teacherAndrzej);
//
//            studentDominik.setUser(user2);
//            studentDominik.getCredits().add(credit1);
//            teacherAndrzej.setUser(user1);
//            teacherAndrzej.getCourses().add(programowanie);
//
//           // programowanie.setCategory(categoryBackEnd);
//            courseRepository.save(programowanie);
//

//            programowanie.getCredits().add(credit1);
//            programowanie.setTeacher(teacherAndrzej);
//            programowanie.setCategory(categoryBackEnd);
//            studentDominik.setUser(user2);
//            studentDominik.getCredits().add(credit1);
//            teacherAndrzej.setUser(user1);
//            teacherAndrzej.getCourses().add(programowanie);
//            categoryBackEnd.getCourses().add(programowanie);
//            studentRepository.save(studentDominik);
//            teacherRepository.save(teacherAndrzej);
//            categoryRepository.save(categoryBackEnd);
//            courseRepository.save(programowanie);
//            creditRepository.save(credit1);

        };
    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    //
    // @Override
    // public void run(String... arg) throws Exception {
    //
    // Teacher Jozef = new Teacher("jozef@gmail.com", "Jozef", "Bomba", "13.01.1992");
    // Student marian = new Student("siemka@gmail.com", "Marian", "Kowalski", "13.01.1992");
    // Credit credit1 = new Credit(5, true);
    // Course programowanie = new Course("Programowanie");
    // Category It = new Category("it");
    // programowanie.setCategory(It);
    // programowanie.setTeacherid(Jozef.getId());
    // credit1.setStudent_id(marian.getId());
    // credit1.setCourse_id(programowanie.getId());
    //
    // teacherRepository.save(Jozef);
    // studentRepository.save(marian);
    // courseRepository.save(programowanie);
    // categoryRepository.save(It);
    // creditRepository.save(credit1);
    //
    // }

}
