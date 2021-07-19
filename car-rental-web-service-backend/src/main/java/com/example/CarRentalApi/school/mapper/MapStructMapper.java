package com.example.CarRentalApi.school.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.CarRentalApi.school.dto.*;
import com.example.CarRentalApi.school.model.*;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

    public List<TeacherDto> teachersToTeachersDto(List<Teacher> teachers);

    public CourseDto courseToCourseDto(Course course);

    public Course courseSlimDtoToCourse(CourseSlimDto course);

    public TeacherSlimDto teacherToTeacherSlimDto(Teacher teacher);

    public Teacher teacherSlimDtoToTeacher(TeacherSlimDto teacherSlimDto);

    public TeacherDto teacherToTeacherDto(Teacher teacher);

    public List<StudentDto> studentsToStudentsDto(List<Student> students);

    public CreditDto creditToCreditDto(Credit credit);

    public CreditSlimDto creditToCreditSlimDto(Credit credit);

    public StudentSlimDto studentToStudentSlimDto(Student student);

    public List<CategoryDto> categoriesToCategoriesDto(List<Category> categories);

    public List<CreditDto> creditsToCreditsDto(List<Credit> credits);

    public List<CourseDto> coursesToCoursesDto(List<Course> courses);

}
