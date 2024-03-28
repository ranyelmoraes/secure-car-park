package com.pitang.securecarpark.securecarpark.exception;

public class LicensePlateUniqueViolationException extends RuntimeException{

    public LicensePlateUniqueViolationException (String message) {
        super(message);
    }
}
