package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.CarRentalApi.school.dto.student.StudentDtoPut;
import com.example.CarRentalApi.school.dto.student.StudentDtoRegister;
import com.example.CarRentalApi.school.dto.user.UserDto;
import com.example.CarRentalApi.school.mapper.CourseMapper;
import com.example.CarRentalApi.school.mapper.StudentMapper;
import com.example.CarRentalApi.school.model.*;
import com.example.CarRentalApi.school.repository.CourseRepository;
import com.example.CarRentalApi.school.repository.CreditRepository;
import com.example.CarRentalApi.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.student.StudentDto;
import com.example.CarRentalApi.school.repository.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, StudentMapper studentMapper, CourseMapper courseMapper, CreditRepository creditRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;

    }

    public List<StudentDto> getStudents() {
        return studentMapper.studentsToStudentsDto(studentRepository.findAll());
    }

    public void addNewStudent(StudentDtoRegister student) {
        Boolean existStudent = existEmail(student).isEmpty();
///

        if (!existStudent)
       {
           throw new IllegalStateException("student with email " +student.getEmail() + "already exist");
       }
//        if (!existUser)
//        {
//            throw new IllegalStateException("student with username " +student.getUser().getUsername() + "already exist");
//        }
       else
        {
            String username = student.getUsername();
            String password = student.getPassword();
            User user = new User(username, password);
            user.setPassword(passwordEncoder.encode(password));
            switch (student.getRole()){
                case "ROLE_ADMIN":
                {
                    Role role = new Role(ERole.ROLE_ADMIN);
                    user.getRoles().add(role);
                    break;
                }
                case "ROLE_MODERATOR":
                {
                    Role role = new Role(ERole.ROLE_MODERATOR);
                    user.getRoles().add(role);
                    break;
                }
                case "ROLE_USER":
                {
                    Role role = new Role(ERole.ROLE_USER);
                    user.getRoles().add(role);
                    break;
                }
                default:{
                    Role role = new Role(ERole.ROLE_USER);
                    user.getRoles().add(role);
                }
            }

            studentRepository.save(studentMapper.studentDtoRegisterToStudent(student));
        }
    }

    public void addNewCourse(Long courseId, StudentDtoPut student) {
        if (existCourse(courseId, student))
        {
            throw new IllegalStateException("student with id " +student.getId() + "already has course " + courseId);
        }

        Optional<Course> existCourse = courseRepository.findById(courseId);
        Course courseToAdd = existCourse
                .orElseThrow(() -> new IllegalStateException("course with id " +courseId + "does not exist"));

        Optional<Student> existStudent = studentRepository.findById(student.getId());

        Student studentToUpdate = existStudent
                .orElseThrow(() -> new IllegalStateException("student with id " + student.getId() + "does not exist"));
        Credit newCredit = new Credit();
        //credit
        newCredit.setStudent(studentToUpdate);
        newCredit.setGrade(2);
        newCredit.setAttempt(false);
        newCredit.setCourse(courseToAdd);
        creditRepository.save(newCredit);
       //student
        studentToUpdate.getCredits().add(newCredit);
    }
    public StudentDto getStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        return studentMapper.studentToStudentsDto(studentRepository.getById(studentId));
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    public void updateStudent(Student student) {
        Optional<Student> exist = studentRepository.findById(student.getId());

        Student studentToUpdate = exist
                .orElseThrow(() -> new IllegalStateException("student with id " + student.getId() + "does not exist"));

        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());

        studentRepository.save(studentToUpdate);

    }

    private Optional<Student> existEmail(StudentDtoRegister student)
    {
       return studentRepository.findByEmail(student.getEmail());
    }
    private Optional<User> existUser(StudentDtoRegister student)
    {
        return userRepository.findByUsername(student.getUsername());
    }
    private boolean existCourse(Long courseId, StudentDtoPut studentDtoPut)
    {
        Optional<Student> existStudent = studentRepository.findById(studentDtoPut.getId());
        List<Course> credits = existStudent.get().getCredits().stream().map(x->x.getCourse()).collect(Collectors.toList());
        List creditsOld = (List) credits.stream().filter(e-> e.getId() == courseId).collect(Collectors.toList());
        if (creditsOld.isEmpty())
            return false;
        else return true;
    }
}
