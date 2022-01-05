package com.example.CarRentalApi.school.controller;

import java.util.List;

import com.example.CarRentalApi.school.dto.course.CourseDtoGet;
import com.example.CarRentalApi.school.dto.course.CourseDtoPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.course.CourseDto;
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
    @GetMapping(path = "/{courseId}")
    public ResponseEntity getCourse(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity((courseService.getCourse(courseId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Course has been created!").build();

    }

    @PostMapping(path = "/{teacherId}")
    public ResponseEntity createCourse(@PathVariable("teacherId") Long teacherId, @RequestBody CourseDtoGet course) {
        courseService.createCourse(teacherId, course);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Course has been created!").build();

    }

    @DeleteMapping(path = "{courseId}")
    public ResponseEntity deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been deleted!").build();
    }

    @PutMapping(path = "/{teacherId}")
    public ResponseEntity updateCourse(@PathVariable("teacherId") Long teacherId,@RequestBody CourseDto course) {
        courseService.updateCourse(teacherId,course);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been updated!").build();
    }
}
