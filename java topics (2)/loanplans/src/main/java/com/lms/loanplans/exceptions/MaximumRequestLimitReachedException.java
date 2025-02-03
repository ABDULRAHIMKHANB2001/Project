package com.lms.loanplans.exceptions;

public class MaximumRequestLimitReachedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MaximumRequestLimitReachedException(String message) {
        super(message);
    }

    public MaximumRequestLimitReachedException(String message, Throwable cause) {
        super(message, cause);
    }
}