package com.example.CarRentalApi.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApi.school.model.Teacher;

import java.util.Optional;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
