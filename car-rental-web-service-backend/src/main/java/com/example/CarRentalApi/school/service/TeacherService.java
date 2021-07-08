package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();

    }

    public void addNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);

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
}
