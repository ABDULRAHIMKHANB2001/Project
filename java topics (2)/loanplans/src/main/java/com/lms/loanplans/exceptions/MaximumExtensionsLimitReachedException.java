package com.lms.loanplans.exceptions;

public class MaximumExtensionsLimitReachedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MaximumExtensionsLimitReachedException(String message) {
        super(message);
    }

    public MaximumExtensionsLimitReachedException(String message, Throwable cause) {
        super(message, cause);
    }
}