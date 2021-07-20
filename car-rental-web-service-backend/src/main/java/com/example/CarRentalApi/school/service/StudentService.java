package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.StudentDto;
import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.repository.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDto> getStudents() {
        return studentMapper.studentsToStudentsDto(studentRepository.findAll());
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);

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

}
