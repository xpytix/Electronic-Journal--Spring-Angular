package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.model.dto.CreditDto;
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
    public ResponseEntity<List<CreditDto>> getCredits() {
        return ResponseEntity.ok(creditService.getCredits());
    }

    @GetMapping("/course")
    public ResponseEntity<List<CreditDto>> getCreditsCourse() {
        return ResponseEntity.ok(creditService.getCreditsCourse());
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
