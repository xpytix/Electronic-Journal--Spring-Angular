package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.student.StudentDto;
import com.example.CarRentalApi.school.dto.student.StudentDtoGet;
import com.example.CarRentalApi.school.dto.student.StudentDtoRegister;
import com.example.CarRentalApi.school.model.Student;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface StudentMapper {
    public StudentDtoGet studentToStudentSlimDto(Student student);
    public StudentDto studentToStudentsDto(Student students);
    public List<StudentDto> studentsToStudentsDto(List<Student> students);
    public Student studentDtoRegisterToStudent(StudentDtoRegister student);
}
