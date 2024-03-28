package com.pitang.securecarpark.securecarpark.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Getter
@ToString
public class ErrorResponse {
    private String message;
    private int errorCode;

    public ErrorResponse(HttpStatus errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode.value();
    }

}