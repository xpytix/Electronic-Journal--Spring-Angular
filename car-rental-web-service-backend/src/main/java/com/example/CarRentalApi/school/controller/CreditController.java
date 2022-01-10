package com.example.CarRentalApi.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CarRentalApi.school.dto.credit.CreditDto;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.service.CreditService;


@RestController
@RequestMapping(path = "api/v1/credit")
public class CreditController {

    private final CreditService creditService;
    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    @GetMapping
    public ResponseEntity<List<CreditDto>> getCredits() {
        return new ResponseEntity<>((creditService.getCredits()), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity addNewCredit(@RequestBody Credit credit) {
        creditService.addNewCredit(credit);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Credit has been created!").build();
    }
    @DeleteMapping(path = "{creditId}")
    public ResponseEntity deleteCredit(@PathVariable("creditId") Long creditId) {
        creditService.deleteCredit(creditId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Credit has been deleted!").build();

    }
    @DeleteMapping(path = "{creditId}")
    public ResponseEntity deleteCreditWithStudent(@PathVariable("creditId") Long creditId) {
        creditService.deleteCredit(creditId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Credit has been deleted!").build();

    }
    @PutMapping
    public ResponseEntity updateCredit(@RequestBody Credit credit) {
        creditService.updateCredit(credit);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Credit has been updated!").build();
    }
}
