package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.dto.CreditDto;
import com.example.CarRentalApi.school.repository.CreditRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository, StudentRepository studentRepository) {
        this.creditRepository = creditRepository;
    }

    public List<CreditDto> getCredits() {
        return creditRepository.findAll()
                .stream().map(credit ->  credit.mapCreditToDto()).collect(Collectors.toList());
    }

    public List<CreditDto> getCreditsCourse() {
        return creditRepository.findAllCreditsWithFetch()
                .stream().map(credit ->  credit.mapCreditToDtoWithCourse()).collect(Collectors.toList());
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
