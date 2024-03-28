package com.pitang.securecarpark.securecarpark.exception;

public class EmailUniqueViolationException extends RuntimeException {

    public EmailUniqueViolationException(String message) {
        super(message);
    }
}
