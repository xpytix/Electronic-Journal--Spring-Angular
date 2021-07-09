package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(teacherService.getTeachers());
    }
    @PostMapping
    public void addNewTeacher(@RequestBody Teacher teacher)
    {
        teacherService.addNewTeacher(teacher);
    }
    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("{teacherId}") Long teacherId){
        teacherService.deleteTeacher(teacherId);
    }
    @PutMapping
    public void updateTeacher( @RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

}