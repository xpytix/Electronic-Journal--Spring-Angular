package com.example.CarRentalApi.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.TeacherDto;
import com.example.CarRentalApi.school.dto.TeacherSlimDto;
import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import com.example.CarRentalApi.school.service.TeacherService;


@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherRepository teacherRepository,
            MapStructMapper mapStructMapper) {
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
        this.mapStructMapper = mapStructMapper;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers() {
        return new ResponseEntity<>((teacherService.getTeachers()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewTeacher(@RequestBody TeacherSlimDto teacher) {
        teacherService.addNewTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Teacher has been created!").build();
    }

    @PutMapping
    public ResponseEntity updateTeacher(@RequestBody TeacherSlimDto teacher) {
        teacherService.updateTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been updated!").build();
    }

    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable("teacherId") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Teacher has been deleted!").build();

    }
    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
    // teacherService.deleteCourse(id);
    // return ResponseEntity.status(HttpStatus.OK).build();
    // }

}
