package com.lms.loanplans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInterestRatesDTO {

    private Integer rateId;
    private String loanType;
    private double interestRate;
}