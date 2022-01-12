package com.example.CarRentalApi.school.controller;

import java.util.List;

import com.example.CarRentalApi.school.dto.teacher.TeacherDtoGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.teacher.TeacherDto;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import com.example.CarRentalApi.school.service.TeacherService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherRepository teacherRepository) {
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return new ResponseEntity<>((teacherService.getTeachers()), HttpStatus.OK);
    }
    @GetMapping(path = "/{teacherId}")
    public ResponseEntity getTeacher(@PathVariable("teacherId") Long teacherId) {
        return new ResponseEntity((teacherService.getTeacher(teacherId)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity addNewTeacher(@Valid @RequestBody TeacherDtoGet teacher) {
        teacherService.addNewTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Teacher has been created!").build();
    }

    @PutMapping
    public ResponseEntity updateTeacher(@Valid @RequestBody TeacherDtoGet teacher) {
        teacherService.updateTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been updated!").build();
    }

    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable("teacherId") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been deleted!").build();

    }


}
