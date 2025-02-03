package com.lms.loanplans.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanPlansDTO {

    private Integer planId;

    @NotNull(message = "Plan name is required")
    private String planName;

    @NotNull(message = "Principal amount is required")
    private Double principalAmount;

    @NotNull(message = "Interest rate is required")
    private Double interestRate;

    @NotNull(message = "Tenure is required")
    private Integer tenure;

    @NotNull(message = "Total payable amount is required")
    private Double totalPayableAmount;

    @NotNull(message = "Plan validity date is required")
    private String planValidity;

    private String planAddedOn;
}