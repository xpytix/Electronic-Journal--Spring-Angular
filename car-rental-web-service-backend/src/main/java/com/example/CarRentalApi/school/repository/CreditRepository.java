package com.example.CarRentalApi.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CarRentalApi.school.model.Credit;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {


}
