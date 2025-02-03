package com.lms.loanplans.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @NotNull(message = "Loan plan ID is required")
    private Integer loanPlanId;

    @NotNull(message = "Plan name is required")
    private String planName;

    @NotNull(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Status is required")
    private String status;

    private String remarks;

    private boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date modifiedOn = new Date();

    @ManyToOne
    @JoinColumn(name = "loanPlanId", insertable = false, updatable = false)
    private LoanPlans loanPlan;

    
}