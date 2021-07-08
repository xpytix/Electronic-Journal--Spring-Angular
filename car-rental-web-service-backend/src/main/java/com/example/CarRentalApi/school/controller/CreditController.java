package com.example.CarRentalApi.school.controller;

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



    public ResponseEntity<List<Credit>> getCredits(){
        return ResponseEntity.ok(creditService.getCredits());
    }

    @DeleteMapping(path = "{creditId}")
    public void deleteCredit(@PathVariable("creditId") Long creditId){
        creditService.deleteCredit(creditId);
    }

}
