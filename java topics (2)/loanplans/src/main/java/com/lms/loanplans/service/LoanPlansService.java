package com.lms.loanplans.service;

import com.lms.loanplans.dto.LoanPlansDTO;
import com.lms.loanplans.exceptions.LoanPlanNotFoundException;

import java.util.List;

public interface LoanPlansService {
    LoanPlansDTO createLoanPlan(LoanPlansDTO loanPlanDTO);
    LoanPlansDTO updateLoanPlan(LoanPlansDTO loanPlanDTO);
    LoanPlansDTO getLoanPlanById(Integer planId) throws LoanPlanNotFoundException;
    List<LoanPlansDTO> getAllLoanPlans();
    void deleteLoanPlan(Integer planId) throws LoanPlanNotFoundException;
}