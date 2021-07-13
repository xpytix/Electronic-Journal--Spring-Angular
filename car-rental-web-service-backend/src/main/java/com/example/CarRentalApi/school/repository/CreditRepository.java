package com.example.CarRentalApi.school.repository;

import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    @Query("SELECT credit from Credit credit join fetch credit.course")
    List<Credit> findAllCreditsWithFetch();

}
