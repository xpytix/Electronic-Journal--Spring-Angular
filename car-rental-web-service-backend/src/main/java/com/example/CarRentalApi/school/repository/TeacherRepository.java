package com.example.CarRentalApi.school.repository;

import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
}
