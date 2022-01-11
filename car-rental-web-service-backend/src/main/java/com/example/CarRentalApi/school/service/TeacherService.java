package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.dto.student.StudentDto;
import com.example.CarRentalApi.school.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.teacher.TeacherDto;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoGet;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.TeacherRepository;


@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;


    @Autowired
    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherDto> getTeachers() {
        return teacherMapper.teachersToTeachersDto(teacherRepository.findAll());
    }

    public void addNewTeacher(TeacherDtoGet teacher) {
        teacherRepository.save(teacherMapper.teacherSlimDtoToTeacher(teacher));
    }

    public void deleteTeacher(Long teacherId) {
        boolean exist = teacherRepository.existsById(teacherId);
        if (!exist) {
            throw new IllegalStateException("teacher with id " + teacherId + "does not exist");
        }
        teacherRepository.deleteById(teacherId);
    }

    public void updateTeacher(TeacherDtoGet teacher) {
        Optional<Teacher> exist = teacherRepository.findById(teacher.getId());

        Teacher teacherToUpdate = exist
                .orElseThrow(() -> new IllegalStateException("teacher with id " + teacher.getId() + "does not exist"));

        teacherToUpdate.setEmail(teacher.getEmail());
        teacherToUpdate.setDateOfBirth(teacher.getDateOfBirth());
        teacherToUpdate.setFirstName(teacher.getFirstName());
        teacherToUpdate.setLastName(teacher.getLastName());

        teacherRepository.save(teacherToUpdate);

    }
    public TeacherDto getTeacher(Long teacherId) {
        boolean exist = teacherRepository.existsById(teacherId);
        if (!exist) {
            throw new IllegalStateException("teacher with id " + teacherId + "does not exist");
        }
        return teacherMapper.teacherToTeacherDto(teacherRepository.getById(teacherId));
    }

}
