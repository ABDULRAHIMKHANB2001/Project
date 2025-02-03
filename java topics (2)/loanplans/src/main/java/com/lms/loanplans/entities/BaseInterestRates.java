package com.lms.loanplans.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInterestRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rateId;

    @NotNull(message = "Loan type is required")
    private String loanType;

    private double interestRate;
}