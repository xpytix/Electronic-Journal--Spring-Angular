package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.dto.course.CourseDtoPut;
import com.example.CarRentalApi.school.dto.teacher.TeacherDto;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoGet;
import com.example.CarRentalApi.school.mapper.CourseMapper;
import com.example.CarRentalApi.school.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.course.CourseDto;
import com.example.CarRentalApi.school.dto.course.CourseDtoGet;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.CourseRepository;
import com.example.CarRentalApi.school.repository.TeacherRepository;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository, CourseMapper courseMapper, TeacherMapper teacherMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.courseMapper = courseMapper;
        this.teacherMapper = teacherMapper;
    }

    public List<CourseDto> getCourses() {
        return courseMapper.coursesToCoursesDto(courseRepository.findAll());
    }

    public CourseDto getCourse(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist) {
            throw new IllegalStateException("credit with id " + courseId + "does not exist");
        }
        return courseMapper.courseToCourseDto(courseRepository.getById(courseId));
    }

    public void createCourse(Long teacherId, CourseDtoGet course) {

        Optional<Teacher> teacherById = teacherRepository.findById(teacherId);
        if (teacherById.isEmpty()) {
            throw new IllegalStateException("credit with id " + teacherId + "does not exist");
        }
        Teacher teacher = teacherById.get();
        Course course1 = courseMapper.courseSlimDtoToCourse(course);

        course1.setTeacher(teacher);
        teacher.getCourses().add(course1);
        courseRepository.save(course1);
    }

    public void deleteCourse(Long courseId) {
        boolean exist = courseRepository.existsById(courseId);
        if (!exist) {
            throw new IllegalStateException("credit with id " + courseId + "does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Long teacherId, CourseDto course) {

        Optional<Course> existCourse = courseRepository.findById(course.getId());
        Optional<Teacher> existTeacher = teacherRepository.findById(teacherId);
        Teacher changeTeacher =  existTeacher
                .orElseThrow(() -> new IllegalStateException("teacher with id " + teacherId + "does not exist"));

        Course courseToUpdate = existCourse
                .orElseThrow(() -> new IllegalStateException("course with id " + course.getId() + "does not exist"));
        courseToUpdate.setName(course.getName());
        courseToUpdate.setTeacher(changeTeacher);
        courseRepository.save(courseToUpdate);
    }


}
