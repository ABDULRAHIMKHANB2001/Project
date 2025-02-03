package com.lms.loanplans.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.loanplans.dto.LoanPlansDTO;
import com.lms.loanplans.entities.LoanPlans;
import com.lms.loanplans.exceptions.LoanPlanNotFoundException;
import com.lms.loanplans.repository.LoanPlansRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanPlansServiceImpl implements LoanPlansService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoanPlansRepository loanPlanRepo;

    @Override
    public LoanPlansDTO createLoanPlan(LoanPlansDTO loanPlanDTO) {
        LoanPlans loanPlan = modelMapper.map(loanPlanDTO, LoanPlans.class);
        loanPlan = loanPlanRepo.save(loanPlan);
        return modelMapper.map(loanPlan, LoanPlansDTO.class);
    }

    @Override
    public LoanPlansDTO updateLoanPlan(LoanPlansDTO loanPlanDTO) {
        LoanPlans existingLoanPlan = loanPlanRepo.findById(loanPlanDTO.getPlanId())
                .orElseThrow(() -> new LoanPlanNotFoundException("Loan plan not found with id: " + loanPlanDTO.getPlanId()));
        
        // Update the existing entity with new values
        existingLoanPlan.setPlanName(loanPlanDTO.getPlanName());
        existingLoanPlan.setPrincipalAmount(loanPlanDTO.getPrincipalAmount());
        existingLoanPlan.setInterestRate(loanPlanDTO.getInterestRate());
        existingLoanPlan.setTenure(loanPlanDTO.getTenure());
        existingLoanPlan.setTotalPayableAmount(loanPlanDTO.getTotalPayableAmount());
        existingLoanPlan.setPlanValidity(loanPlanDTO.getPlanValidity());
        existingLoanPlan.setPlanAddedOn(loanPlanDTO.getPlanAddedOn());

        LoanPlans updatedLoanPlan = loanPlanRepo.save(existingLoanPlan);
        return modelMapper.map(updatedLoanPlan, LoanPlansDTO.class);
    }

    @Override
    public LoanPlansDTO getLoanPlanById(Integer planId) throws LoanPlanNotFoundException {
        LoanPlans loanPlan = loanPlanRepo.findById(planId)
                .orElseThrow(() -> new LoanPlanNotFoundException("Loan plan not found with id: " + planId));
        return modelMapper.map(loanPlan, LoanPlansDTO.class);
    }

    @Override
    public List<LoanPlansDTO> getAllLoanPlans() {
        List<LoanPlans> loanPlans = loanPlanRepo.findAll();
        return loanPlans.stream()
                .map(loanPlan -> modelMapper.map(loanPlan, LoanPlansDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLoanPlan(Integer planId) throws LoanPlanNotFoundException {
        LoanPlans loanPlan = loanPlanRepo.findById(planId)
                .orElseThrow(() -> new LoanPlanNotFoundException("Loan plan not found with id: " + planId));
        loanPlanRepo.delete(loanPlan);
    }
}