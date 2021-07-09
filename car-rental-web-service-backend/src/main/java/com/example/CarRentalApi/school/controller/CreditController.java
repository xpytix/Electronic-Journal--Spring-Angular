package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }


    @GetMapping
    public ResponseEntity<List<Credit>> getCredits() {
        List<Credit> creditList = creditService.getCredits();
        if (creditList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(creditList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(creditList);
    }

    @PostMapping
    public ResponseEntity addNewCredit(@RequestBody Credit credit)
    {
        creditService.addNewCredit(credit);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Credit has been created!").build();

    }

    @DeleteMapping(path = "{creditId}")
    public ResponseEntity deleteCredit(@PathVariable("creditId") Long creditId){
        creditService.deleteCredit(creditId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Credit has been deleted!").build();

    }
    @PutMapping
    public ResponseEntity updateCredit(@RequestBody Credit credit) {
        creditService.updateCredit(credit);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Credit has been updated!").build();
    }
}
