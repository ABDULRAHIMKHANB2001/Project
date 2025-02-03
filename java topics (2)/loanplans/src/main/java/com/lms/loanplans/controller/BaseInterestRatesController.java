package com.lms.loanplans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.loanplans.dto.BaseInterestRatesDTO;
import com.lms.loanplans.service.BaseInterestRatesService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/baseinterestrates")
@SecurityRequirement(name = "Bearer Authentication")
public class BaseInterestRatesController {

    @Autowired
    private BaseInterestRatesService baseInterestRatesService;

    @PostMapping
    public ResponseEntity<BaseInterestRatesDTO> createBaseInterestRate(@RequestBody BaseInterestRatesDTO baseInterestRatesDTO) {
        BaseInterestRatesDTO createdBaseInterestRate = baseInterestRatesService.createBaseInterestRate(baseInterestRatesDTO);
        return new ResponseEntity<>(createdBaseInterestRate, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BaseInterestRatesDTO> updateBaseInterestRate(@RequestBody BaseInterestRatesDTO baseInterestRatesDTO) {
        BaseInterestRatesDTO updatedBaseInterestRate = baseInterestRatesService.updateBaseInterestRate(baseInterestRatesDTO);
        return new ResponseEntity<>(updatedBaseInterestRate, HttpStatus.OK);
    }

    @GetMapping("/{rateId}")
    public ResponseEntity<BaseInterestRatesDTO> getBaseInterestRateById(@PathVariable Integer rateId) {
        BaseInterestRatesDTO baseInterestRatesDTO = baseInterestRatesService.getBaseInterestRateById(rateId);
        if (baseInterestRatesDTO != null) {
            return ResponseEntity.ok(baseInterestRatesDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('BANK MANAGER')")
    public ResponseEntity<List<BaseInterestRatesDTO>> getAllBaseInterestRates(Authentication authentication) {
        List<BaseInterestRatesDTO> baseInterestRatesList = baseInterestRatesService.getAllBaseInterestRates();
        return ResponseEntity.ok(baseInterestRatesList);
    }
}