package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.dto.StudentDto;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<StudentDto> getStudents(){
        return studentRepository.findAll()
                .stream().map(student ->  student.mapStudentWithCredits()).collect(Collectors.toList());

    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }


    public void updateStudent(Student student) {
        Optional<Student> exist = studentRepository.findById(student.getId());

        Student studentToUpdate = exist.orElseThrow(()-> new IllegalStateException(
                "student with id " + student.getId() + "does not exist")
        );

        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());

        studentRepository.save(studentToUpdate);

    }

}