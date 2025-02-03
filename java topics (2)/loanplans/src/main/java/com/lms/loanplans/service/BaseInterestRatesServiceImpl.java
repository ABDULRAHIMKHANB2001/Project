package com.lms.loanplans.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.loanplans.dto.BaseInterestRatesDTO;
import com.lms.loanplans.entities.BaseInterestRates;
import com.lms.loanplans.exceptions.BaseInterestRateNotFoundException;
import com.lms.loanplans.repository.BaseInterestRatesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseInterestRatesServiceImpl implements BaseInterestRatesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepo;

    @Override
    public BaseInterestRatesDTO createBaseInterestRate(BaseInterestRatesDTO baseInterestRatesDTO) {
        BaseInterestRates baseInterestRate = modelMapper.map(baseInterestRatesDTO, BaseInterestRates.class);
        baseInterestRate = baseInterestRatesRepo.save(baseInterestRate);
        return modelMapper.map(baseInterestRate, BaseInterestRatesDTO.class);
    }

    @Override
    public BaseInterestRatesDTO updateBaseInterestRate(BaseInterestRatesDTO baseInterestRatesDTO) {
        BaseInterestRates baseInterestRate = modelMapper.map(baseInterestRatesDTO, BaseInterestRates.class);
        baseInterestRate = baseInterestRatesRepo.save(baseInterestRate);
        return modelMapper.map(baseInterestRate, BaseInterestRatesDTO.class);
    }

    @Override
    public BaseInterestRatesDTO getBaseInterestRateById(Integer rateId) throws BaseInterestRateNotFoundException {
        BaseInterestRates baseInterestRate = baseInterestRatesRepo.findById(rateId)
                .orElseThrow(() -> new BaseInterestRateNotFoundException("Base interest rate not found with id: " + rateId));
        return modelMapper.map(baseInterestRate, BaseInterestRatesDTO.class);
    }

    @Override
    public List<BaseInterestRatesDTO> getAllBaseInterestRates() {
        List<BaseInterestRates> baseInterestRates = baseInterestRatesRepo.findAll();
        return baseInterestRates.stream()
                .map(baseInterestRate -> modelMapper.map(baseInterestRate, BaseInterestRatesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBaseInterestRate(Integer rateId) throws BaseInterestRateNotFoundException {
        BaseInterestRates baseInterestRate = baseInterestRatesRepo.findById(rateId)
                .orElseThrow(() -> new BaseInterestRateNotFoundException("Base interest rate not found with id: " + rateId));
        baseInterestRatesRepo.delete(baseInterestRate);
    }
}
