package com.lms.loanplans.service;

import com.lms.loanplans.dto.LoanHistoryDTO;
import com.lms.loanplans.exceptions.LoanHistoryNotFoundException;

import java.util.List;

public interface LoanHistoryService {
    LoanHistoryDTO createLoanHistory(LoanHistoryDTO loanHistoryDTO);
    LoanHistoryDTO updateLoanHistory(LoanHistoryDTO loanHistoryDTO);
    LoanHistoryDTO getLoanHistoryById(Integer historyId) throws LoanHistoryNotFoundException;
    List<LoanHistoryDTO> getAllLoanHistories();
    void deleteLoanHistory(Integer historyId, Integer version) throws LoanHistoryNotFoundException;
}