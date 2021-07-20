package com.example.CarRentalApi.school.controller;

import java.util.List;

import com.example.CarRentalApi.school.dto.CourseDtoGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.CourseDto;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.service.CourseService;


@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses() {
        return new ResponseEntity<>((courseService.getCourses()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Course has been created!").build();

    }

    @PostMapping(path = "{teacherId}")
    public ResponseEntity createCourse(@PathVariable("teacherId") Long teacherId, CourseDtoGet course) {
        courseService.createCourse(teacherId, course);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Course has been created!").build();

    }

    @DeleteMapping(path = "{courseId}")
    public ResponseEntity deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been deleted!").build();
    }

    @PutMapping
    public ResponseEntity updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been updated!").build();
    }
}
