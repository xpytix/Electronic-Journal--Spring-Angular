package com.example.CarRentalApi.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApi.school.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
