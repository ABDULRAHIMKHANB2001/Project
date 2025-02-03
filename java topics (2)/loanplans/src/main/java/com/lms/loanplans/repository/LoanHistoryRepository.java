package com.lms.loanplans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.loanplans.entities.LoanHistory;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Integer> {
    // Custom query methods can be added here if needed
}