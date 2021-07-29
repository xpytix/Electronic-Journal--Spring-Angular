package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.course.CourseDto;
import com.example.CarRentalApi.school.dto.course.CourseDtoGet;
import com.example.CarRentalApi.school.model.Course;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CourseMapper {
    public List<CourseDto> coursesToCoursesDto(List<Course> courses);

    public CourseDto courseToCourseDto(Course course);

    public Course courseSlimDtoToCourse(CourseDtoGet course);
}
