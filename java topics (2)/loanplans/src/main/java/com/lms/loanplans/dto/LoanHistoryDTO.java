package com.lms.loanplans.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanHistoryDTO {

    private Integer historyId;

    @NotNull(message = "Loan plan ID is required")
    private Integer loanPlanId;

    @NotBlank(message = "Plan name is required")
    private String planName;

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Status is required")
    private String status;

    private String remarks;

    private boolean isActive;

    private Date modifiedOn;

    
}