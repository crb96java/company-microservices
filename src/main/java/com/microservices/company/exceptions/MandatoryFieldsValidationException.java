package com.microservices.company.exceptions;

public class MandatoryFieldsValidationException extends RuntimeException {
    public MandatoryFieldsValidationException(String message) {
        super(message);
    }
}
