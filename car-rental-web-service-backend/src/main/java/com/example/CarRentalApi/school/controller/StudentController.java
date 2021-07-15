package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.dto.StudentDto;
import com.example.CarRentalApi.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent(){
        return new ResponseEntity<>((
                studentService.getStudents()
        ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Student has been created!").build();

    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Student has been deleted!").build();

    }
    @PutMapping
    public ResponseEntity updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Student has been updated!").build();

    }

}