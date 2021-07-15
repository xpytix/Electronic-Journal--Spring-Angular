package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.dto.TeacherDto;
import com.example.CarRentalApi.school.dto.TeacherSlimDto;
import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, MapStructMapper mapStructMapper) {
        this.teacherRepository = teacherRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public List<TeacherDto> getTeachers(){
        return   mapStructMapper.teachersToTeachersDto(
                teacherRepository.findAll());
    }

    public void addNewTeacher(TeacherSlimDto teacher) {
        teacherRepository.save(mapStructMapper.teacherSlimDtoToTeacher(teacher));
    }

    public void deleteTeacher(Long teacherId) {
        boolean exist = teacherRepository.existsById(teacherId);
        if(!exist){
            throw new IllegalStateException(
                    "teacher with id " + teacherId + "does not exist");
        }
        teacherRepository.deleteById(teacherId);
    }

    public void updateTeacher(Teacher teacher) {
        Optional<Teacher> exist = teacherRepository.findById(teacher.getId());

        Teacher teacherToUpdate = exist.orElseThrow(()-> new IllegalStateException(
                "teacher with id " + teacher.getId() + "does not exist")
        );

        teacherToUpdate.setEmail(teacher.getEmail());
        teacherToUpdate.setDateOfBirth(teacher.getDateOfBirth());
        teacherToUpdate.setFirstName(teacher.getFirstName());
        teacherToUpdate.setLastName(teacher.getLastName());

        teacherRepository.save(teacherToUpdate);

    }
    // MAP TEACHER TO DTO

}
