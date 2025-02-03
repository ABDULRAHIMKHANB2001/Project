package com.lms.loanplans.exceptions;

public class InvalidRequestStatusException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidRequestStatusException(String message) {
        super(message);
    }

    public InvalidRequestStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}