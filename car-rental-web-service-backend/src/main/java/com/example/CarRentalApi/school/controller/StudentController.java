package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Student>> getStudent(){
        return ResponseEntity.ok(studentService.getStudents());
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping
    public void updateStudent( @RequestBody Student student) {
        studentService.updateStudent(student);
    }

}