package com.lms.loanplans.exceptions;

public class BaseInterestRateNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public BaseInterestRateNotFoundException(String message) {
        super(message);
    }
}