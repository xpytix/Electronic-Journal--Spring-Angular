package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(){
        List<Teacher> teacherList = teacherService.getTeachers();
        if (teacherList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(teacherList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacherList);
    }
    @PostMapping
    public ResponseEntity addNewTeacher(@RequestBody Teacher teacher)
    {
        teacherService.addNewTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Teacher has been created!").build();

    }
    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable("{teacherId}") Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been deleted!").build();

    }
    @PutMapping
    public ResponseEntity updateTeacher( @RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been updated!").build();

    }

}