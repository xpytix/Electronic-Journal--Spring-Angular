package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.dto.CourseDto;
import com.example.CarRentalApi.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, MapStructMapper mapStructMapper) {
        this.courseRepository = courseRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public List<CourseDto> getCourses(){
        return  mapStructMapper.coursesToCoursesDto(
                courseRepository.findAll());
    }

    public void deleteCourse(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if(!exist){
            throw new IllegalStateException(
                    "credit with id " + courseId + "does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        Optional<Course> exist = courseRepository.findById(course.getId());

        Course courseToUpdate = exist.orElseThrow(()-> new IllegalStateException(
                "course with id " + course.getId() + "does not exist")
        );
        courseToUpdate.setName(course.getName());

    }

}
