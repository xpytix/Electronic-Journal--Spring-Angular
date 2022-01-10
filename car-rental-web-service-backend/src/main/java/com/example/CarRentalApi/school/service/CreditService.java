package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.mapper.CreditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.credit.CreditDto;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.repository.CreditRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;

@Service
public class CreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    @Autowired
    public CreditService(CreditRepository creditRepository, StudentRepository studentRepository, CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    public List<CreditDto> getCredits() {
        return creditMapper.creditsToCreditsDto(creditRepository.findAll());
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
    public void deleteCreditWithStudent(Credit credit)
    {
        //to do
    }
    public void updateCredit(Credit credit) {
        Optional<Credit> exist = creditRepository.findById(credit.getId());
        Credit creditToUpdate = exist
                .orElseThrow(() -> new IllegalStateException("category with id " + credit.getId() + "does not exist"));
        creditToUpdate.setGrade(credit.getGrade());
        creditToUpdate.setAttempt(credit.getAttempt());

        creditRepository.save(creditToUpdate);
    }
}
