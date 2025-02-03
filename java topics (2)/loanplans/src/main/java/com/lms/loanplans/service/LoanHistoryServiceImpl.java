package com.lms.loanplans.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.loanplans.dto.LoanHistoryDTO;
import com.lms.loanplans.entities.LoanHistory;
import com.lms.loanplans.exceptions.LoanHistoryNotFoundException;
import com.lms.loanplans.repository.LoanHistoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanHistoryServiceImpl implements LoanHistoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoanHistoryRepository loanHistoryRepo;

    @Override
    public LoanHistoryDTO createLoanHistory(LoanHistoryDTO loanHistoryDTO) {
        LoanHistory loanHistory = modelMapper.map(loanHistoryDTO, LoanHistory.class);
        loanHistory = loanHistoryRepo.save(loanHistory);
        return modelMapper.map(loanHistory, LoanHistoryDTO.class);
    }

    @Override
    public LoanHistoryDTO updateLoanHistory(LoanHistoryDTO loanHistoryDTO) {
        LoanHistory loanHistory = modelMapper.map(loanHistoryDTO, LoanHistory.class);
        loanHistory = loanHistoryRepo.save(loanHistory);
        return modelMapper.map(loanHistory, LoanHistoryDTO.class);
    }

    @Override
    public LoanHistoryDTO getLoanHistoryById(Integer historyId) throws LoanHistoryNotFoundException {
        LoanHistory loanHistory = loanHistoryRepo.findById(historyId)
                .orElseThrow(() -> new LoanHistoryNotFoundException("Loan history not found with id: " + historyId));
        return modelMapper.map(loanHistory, LoanHistoryDTO.class);
    }

    @Override
    public List<LoanHistoryDTO> getAllLoanHistories() {
        List<LoanHistory> loanHistories = loanHistoryRepo.findAll();
        return loanHistories.stream()
                .map(loanHistory -> modelMapper.map(loanHistory, LoanHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLoanHistory(Integer historyId, Integer version) throws LoanHistoryNotFoundException {
        LoanHistory loanHistory = loanHistoryRepo.findById(historyId)
                .orElseThrow(() -> new LoanHistoryNotFoundException("Loan history not found with id: " + historyId));
        loanHistoryRepo.delete(loanHistory);
    }
}