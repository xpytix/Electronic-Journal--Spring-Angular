package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.repository.CreditRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository, StudentRepository studentRepository) {
        this.creditRepository = creditRepository;
        this.studentRepository = studentRepository;
    }

    public List<Credit> getCredits() {
        return creditRepository.findAll();

    }

    public void addNewCredit(Credit credit) {
        creditRepository.save(credit);
    }

    public void deleteCredit(Long creditId) {
        boolean exist = creditRepository.existsById(creditId);
        if (!exist) {
            throw new IllegalStateException("credit with id " + creditId + "does not exist");
        }
        creditRepository.deleteById(creditId);
    }

    public void updateCredit(Credit credit) {
        Optional<Credit> exist = creditRepository.findById(credit.getId());

        Credit creditToUpdate = exist.orElseThrow(
                () -> new IllegalStateException("category with id " + credit.getId() + "does not exist"));
        creditToUpdate.setGrade(credit.getGrade());
        creditToUpdate.setAttempt(credit.getAttempt());

        creditRepository.save(creditToUpdate);
    }
}
