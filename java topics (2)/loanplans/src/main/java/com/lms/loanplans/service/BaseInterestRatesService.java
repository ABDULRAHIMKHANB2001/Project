package com.lms.loanplans.service;

import com.lms.loanplans.dto.BaseInterestRatesDTO;
import com.lms.loanplans.exceptions.BaseInterestRateNotFoundException;

import java.util.List;

public interface BaseInterestRatesService {
    BaseInterestRatesDTO createBaseInterestRate(BaseInterestRatesDTO baseInterestRatesDTO);
    BaseInterestRatesDTO updateBaseInterestRate(BaseInterestRatesDTO baseInterestRatesDTO);
    BaseInterestRatesDTO getBaseInterestRateById(Integer rateId) throws BaseInterestRateNotFoundException;
    List<BaseInterestRatesDTO> getAllBaseInterestRates();
    void deleteBaseInterestRate(Integer rateId) throws BaseInterestRateNotFoundException;
}