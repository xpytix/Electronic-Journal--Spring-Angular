package com.example.CarRentalApi;
import com.example.CarRentalApi.school.model.*;
import com.example.CarRentalApi.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class CarRentalApiApplication {

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

    @Bean
    public CommandLineRunner dataLoader(CategoryRepository categoryRepository, CourseRepository courseRepository, CreditRepository creditRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        return args -> {
            Teacher teacherJozef = new Teacher(
                    "jozef@gmail.com","Jozef", "Bomba",
                    "13.01.1992"
            );
            Teacher teacherAndrzej = new Teacher(
                    "andrzeja@gmail.com","Andrzej", "Nuda",
                    "1.11.2009"
            );

            Course programowanie = new Course(
                    "Programowanie"
            );

            Course bazy_danych = new Course(
                    "bazy_danych"
            );

            Student studentDominik = new Student("siemka@gmail.com", "Dominik", "Kowalski", "13.01.1992");
            Student studentSzymon = new Student("siemka@gmail.com", "Szymon", "Kowalski", "13.01.1992");

            Credit credit1 = new Credit(5, true);
            Credit credit2 = new Credit(5, true);
            Credit credit3 = new Credit(5, true);
            Category categoryFrontEnd = new Category(
                    "FrontEnd"
            );
            Category categoryBackEnd = new Category(
                    "BackEnd"
            );


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


            programowanie.setCredit(credit1);
            categoryBackEnd.addCourse(programowanie);
            categoryFrontEnd.addCourse(bazy_danych);


            credit1.setCourse(programowanie);
            credit2.setCourse(programowanie);
            credit3.setCourse(programowanie);
            credit1.setStudent(studentDominik);
            credit2.setStudent(studentDominik);
            credit3.setStudent(studentSzymon);

            creditRepository.save(credit1);


        };
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
