package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.model.dto.CourseDto;
import com.example.CarRentalApi.school.model.dto.TeacherDto;
import com.example.CarRentalApi.school.repository.CourseRepository;
import com.example.CarRentalApi.school.repository.CreditRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDto> getCourses(){
        return courseRepository.findAllWithFetch()
                .stream().map(course ->  course.mapCourseToDtoWithTeacher()).collect(Collectors.toList());
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
