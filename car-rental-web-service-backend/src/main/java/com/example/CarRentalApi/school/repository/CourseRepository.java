package com.example.CarRentalApi.school.repository;

import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT course from Course course join fetch course.teacher")
    List<Course> findAllWithFetch();

}
