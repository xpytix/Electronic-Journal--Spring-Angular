package com.example.CarRentalApi.school.repository;

import com.example.CarRentalApi.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
}
