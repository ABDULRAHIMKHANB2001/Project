package com.lms.loanplans.exceptions;

public class LoanHistoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoanHistoryNotFoundException(String message) {
        super(message);
    }
}