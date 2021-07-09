package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courseList = courseService.getCourses();
        if (courseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }
    @PostMapping
    public ResponseEntity addNewCourse(@RequestBody Course course)
    {
        courseService.addNewCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Course has been created!").build();

    }
    @DeleteMapping(path = "{courseId}")
    public ResponseEntity deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been deleted!").build();
    }

    @PutMapping
    public ResponseEntity updateCourse( @RequestBody Course course) {
        courseService.updateCourse(course);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Course has been updated!").build();
    }
}
