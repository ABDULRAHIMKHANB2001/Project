package com.lms.loanplans.exceptions;

public class LoanPaymentCompletedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoanPaymentCompletedException(String message) {
        super(message);
    }

    public LoanPaymentCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}