package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.CourseDto;
import com.example.CarRentalApi.school.dto.CourseSlimDto;
import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.CourseRepository;
import com.example.CarRentalApi.school.repository.TeacherRepository;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository,
            MapStructMapper mapStructMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public List<CourseDto> getCourses() {
        return mapStructMapper.coursesToCoursesDto(courseRepository.findAll());
    }

    public void createCourse(Long teacherId, CourseSlimDto course) {

        Optional<Teacher> byId = teacherRepository.findById(teacherId);
        if (!byId.isPresent()) {
            throw new IllegalStateException("credit with id " + teacherId + "does not exist");
        }

        Teacher teacher = byId.get();
        Course course1 = mapStructMapper.courseSlimDtoToCourse(course);

        course1.setTeacher(teacher);
        teacher.getCourses().add(course1);

        teacherRepository.save(teacher);

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

    public void updateCourse(Course course) {
        Optional<Course> exist = courseRepository.findById(course.getId());

        Course courseToUpdate = exist
                .orElseThrow(() -> new IllegalStateException("course with id " + course.getId() + "does not exist"));
        courseToUpdate.setName(course.getName());

    }

}
