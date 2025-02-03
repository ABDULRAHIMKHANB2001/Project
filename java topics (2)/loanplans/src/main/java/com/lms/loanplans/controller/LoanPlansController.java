package com.lms.loanplans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.loanplans.dto.LoanPlansDTO;
import com.lms.loanplans.exceptions.LoanPlanNotFoundException;
import com.lms.loanplans.service.LoanPlansService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/loanplans")
@SecurityRequirement(name = "Bearer Authentication")
public class LoanPlansController {

    @Autowired
    private LoanPlansService loanPlanService;

    @PostMapping
    @PreAuthorize("hasAuthority('BANK MANAGER')")
    public ResponseEntity<LoanPlansDTO> createLoanPlan(@RequestBody LoanPlansDTO loanPlanDTO, Authentication authentication) {
        LoanPlansDTO createdLoanPlan = loanPlanService.createLoanPlan(loanPlanDTO);
        return new ResponseEntity<>(createdLoanPlan, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('BANK MANAGER')")
    public ResponseEntity<LoanPlansDTO> updateLoanPlan(@RequestBody LoanPlansDTO loanPlanDTO, Authentication authentication) {
        LoanPlansDTO updatedLoanPlan = loanPlanService.updateLoanPlan(loanPlanDTO);
        return new ResponseEntity<>(updatedLoanPlan, HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    @PreAuthorize("hasAnyAuthority('BANK MANAGER', 'CUSTOMER')")
    public ResponseEntity<LoanPlansDTO> getLoanPlanById(@PathVariable Integer planId, Authentication authentication) {
        LoanPlansDTO loanPlanDTO = loanPlanService.getLoanPlanById(planId);
        if (loanPlanDTO != null) {
            return ResponseEntity.ok(loanPlanDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('BANK MANAGER', 'CUSTOMER')")
    public ResponseEntity<List<LoanPlansDTO>> getAllLoanPlans(Authentication authentication) {
        List<LoanPlansDTO> loanPlanList = loanPlanService.getAllLoanPlans();
        return ResponseEntity.ok(loanPlanList);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deleteLoanPlan(@PathVariable Integer planId) {
        try {
            loanPlanService.deleteLoanPlan(planId);
            return ResponseEntity.noContent().build();
        } catch (LoanPlanNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}