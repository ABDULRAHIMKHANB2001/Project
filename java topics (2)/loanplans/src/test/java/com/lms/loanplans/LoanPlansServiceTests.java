package com.lms.loanplans;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.lms.loanplans.dto.LoanPlansDTO;
import com.lms.loanplans.entities.LoanPlans;
import com.lms.loanplans.exceptions.LoanPlanNotFoundException;
import com.lms.loanplans.repository.LoanPlansRepository;
import com.lms.loanplans.service.LoanPlansServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LoanPlansServiceTests {

    @Mock
    private LoanPlansRepository loanPlanRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LoanPlansServiceImpl loanPlansService;

    @Test
    public void testCreateLoanPlan() {
        LoanPlansDTO loanPlanDTO = new LoanPlansDTO();
        LoanPlans loanPlan = new LoanPlans();
        
        given(modelMapper.map(loanPlanDTO, LoanPlans.class)).willReturn(loanPlan);
        given(loanPlanRepo.save(loanPlan)).willReturn(loanPlan);
        given(modelMapper.map(loanPlan, LoanPlansDTO.class)).willReturn(loanPlanDTO);
        
        LoanPlansDTO result = loanPlansService.createLoanPlan(loanPlanDTO);
        
        assertNotNull(result);
        verify(loanPlanRepo, times(1)).save(loanPlan);
    }

    @Test
    public void testUpdateLoanPlan() {
        LoanPlansDTO loanPlanDTO = new LoanPlansDTO();
        loanPlanDTO.setPlanId(1);
        LoanPlans existingLoanPlan = new LoanPlans();
        
        given(loanPlanRepo.findById(loanPlanDTO.getPlanId())).willReturn(Optional.of(existingLoanPlan));
        given(loanPlanRepo.save(existingLoanPlan)).willReturn(existingLoanPlan);
        given(modelMapper.map(existingLoanPlan, LoanPlansDTO.class)).willReturn(loanPlanDTO);
        
        LoanPlansDTO result = loanPlansService.updateLoanPlan(loanPlanDTO);
        
        assertNotNull(result);
        verify(loanPlanRepo, times(1)).findById(loanPlanDTO.getPlanId());
        verify(loanPlanRepo, times(1)).save(existingLoanPlan);
    }

    @Test
    public void testGetLoanPlanById() throws LoanPlanNotFoundException {
        LoanPlans loanPlan = new LoanPlans();
        LoanPlansDTO loanPlanDTO = new LoanPlansDTO();
        
        given(loanPlanRepo.findById(1)).willReturn(Optional.of(loanPlan));
        given(modelMapper.map(loanPlan, LoanPlansDTO.class)).willReturn(loanPlanDTO);
        
        LoanPlansDTO result = loanPlansService.getLoanPlanById(1);
        
        assertNotNull(result);
        verify(loanPlanRepo, times(1)).findById(1);
    }

    @Test
    public void testGetLoanPlanById_NotFound() {
        given(loanPlanRepo.findById(1)).willReturn(Optional.empty());
        
        assertThrows(LoanPlanNotFoundException.class, () -> loanPlansService.getLoanPlanById(1));
        verify(loanPlanRepo, times(1)).findById(1);
    }

    @Test
    public void testGetAllLoanPlans() {
        LoanPlans loanPlan = new LoanPlans();
        List<LoanPlans> loanPlans = Arrays.asList(loanPlan);
        LoanPlansDTO loanPlanDTO = new LoanPlansDTO();
        List<LoanPlansDTO> loanPlansDTOs = Arrays.asList(loanPlanDTO);
        
        given(loanPlanRepo.findAll()).willReturn(loanPlans);
        given(modelMapper.map(loanPlan, LoanPlansDTO.class)).willReturn(loanPlanDTO);
        
        List<LoanPlansDTO> result = loanPlansService.getAllLoanPlans();
        
        assertNotNull(result);
        verify(loanPlanRepo, times(1)).findAll();
    }

    @Test
    public void testDeleteLoanPlan() throws LoanPlanNotFoundException {
        LoanPlans loanPlan = new LoanPlans();
        
        given(loanPlanRepo.findById(1)).willReturn(Optional.of(loanPlan));
        
        loanPlansService.deleteLoanPlan(1);
        
        verify(loanPlanRepo, times(1)).delete(loanPlan);
    }

    @Test
    public void testDeleteLoanPlan_NotFound() {
        given(loanPlanRepo.findById(1)).willReturn(Optional.empty());
        
        assertThrows(LoanPlanNotFoundException.class, () -> loanPlansService.deleteLoanPlan(1));
        verify(loanPlanRepo, times(1)).findById(1);
    }
}