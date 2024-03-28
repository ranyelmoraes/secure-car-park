package com.pitang.securecarpark.securecarpark.exception;

public class LoginUniqueViolationException extends RuntimeException {
    public LoginUniqueViolationException (String message) {
        super(message);
    }
}
