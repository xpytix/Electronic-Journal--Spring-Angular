package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
        //return ResponseEntity.ok(courseService.getCourses());
        return ResponseEntity.ok(courseService.getCourses());
    }
    @PostMapping
    public void addNewCourse(@RequestBody Course course)
    {
        courseService.addNewCourse(course);
    }
    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }
    @PutMapping
    public void updateCourse( @RequestBody Course course) {
        courseService.updateCourse(course);
    }
}
