package com.microservices.company.exceptions;

public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
            super(message);
    }
}
