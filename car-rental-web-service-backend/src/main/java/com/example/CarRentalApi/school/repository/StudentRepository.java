package com.example.CarRentalApi.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApi.school.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
