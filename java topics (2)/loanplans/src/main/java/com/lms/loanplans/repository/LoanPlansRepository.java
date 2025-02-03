package com.lms.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.loanplans.entities.LoanPlans;

@Repository
public interface LoanPlansRepository extends JpaRepository<LoanPlans, Integer> {
    LoanPlans findByPlanName(String planName);
}