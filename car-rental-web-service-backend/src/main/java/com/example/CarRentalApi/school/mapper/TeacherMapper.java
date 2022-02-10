package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.teacher.TeacherDto;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoGet;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoRegister;
import com.example.CarRentalApi.school.model.Teacher;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TeacherMapper {
    public List<TeacherDto> teachersToTeachersDto(List<Teacher> teachers);

    public TeacherDtoGet teacherToTeacherSlimDto(Teacher teacher);

    public Teacher teacherSlimDtoToTeacher(TeacherDtoGet teacherDtoGet);

    public TeacherDto teacherToTeacherDto(Teacher teacher);
    public Teacher teacherDtoRegisterToStudent(TeacherDtoRegister teacherDtoRegister);
}
