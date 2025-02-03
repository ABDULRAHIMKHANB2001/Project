package com.lms.loanplans.exceptions;

public class LoanPlanNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoanPlanNotFoundException(String message) {
        super(message);
    }
}