package com.lms.loanplans.exceptions;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final Map<String, String> validationErrors;

    public ValidationException(String message, Map<String, String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
