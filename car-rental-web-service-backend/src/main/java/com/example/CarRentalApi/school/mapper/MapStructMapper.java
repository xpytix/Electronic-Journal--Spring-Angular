package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.*;
import com.example.CarRentalApi.school.model.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    public List<TeacherDto> teachersToTeachersDto(List<Teacher> teachers);
    public CourseDto courseToCourseDto(Course course);
    public TeacherSlimDto teacherToTeacherSlimDto(Teacher teacher);
    public List<StudentDto> studentsToStudentsDto(List<Student> students);
    public CreditDto creditToCreditDto(Credit credit);
    public CreditSlimDto creditToCreditSlimDto(Credit credit);
    public StudentSlimDto studentToStudentSlimDto(Student student);
    public List<CategoryDto> categoriesToCategoriesDto(List<Category> categories);
    public List<CreditDto> creditsToCreditsDto(List<Credit> credits);
    public List<CourseDto> coursesToCoursesDto(List<Course> courses);


}
