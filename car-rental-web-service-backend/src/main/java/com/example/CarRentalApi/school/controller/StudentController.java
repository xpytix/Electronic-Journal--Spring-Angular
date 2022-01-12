package com.example.CarRentalApi.school.controller;

import java.util.List;

import com.example.CarRentalApi.school.dto.student.StudentDtoPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.student.StudentDto;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.service.StudentService;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        return new ResponseEntity<>((studentService.getStudents()), HttpStatus.OK);
    }
    @GetMapping(path = "/{studentId}")
    public ResponseEntity getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity((studentService.getStudent(studentId)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity registerNewStudent(@Valid @RequestBody Student student) {
        studentService.addNewStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Student has been created!").build();
    }
    @PostMapping(path = "{courseId}")
    public ResponseEntity addNewCourse(@PathVariable("courseId") Long courseId,@Valid @RequestBody StudentDtoPut student) {
        studentService.addNewCourse(courseId, student);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Student has been created!").build();
    }
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Student has been deleted!").build();

    }

    @PutMapping
    public ResponseEntity updateStudent(@Valid @RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Student has been updated!").build();

    }

}
