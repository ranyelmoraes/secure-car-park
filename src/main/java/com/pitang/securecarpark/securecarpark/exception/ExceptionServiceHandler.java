package com.pitang.securecarpark.securecarpark.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionServiceHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(RuntimeException ex) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),  ex.getMessage()));
    }

    @ExceptionHandler(LoginUniqueViolationException.class)
    public ResponseEntity<ErrorResponse> uniqueLoginViolationException(RuntimeException ex) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse( HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(EmailUniqueViolationException.class)
    public ResponseEntity<ErrorResponse> uniqueEmailViolationException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse( HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        boolean hasNullEmptyFields = fieldErrors.stream()
                .anyMatch(error -> Objects.requireNonNull(error.getDefaultMessage()).contains("branco") || error.getDefaultMessage().contains("maior que 0"));

        String message = hasNullEmptyFields ? "Missing fields”" : "Invalid fields";

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse( HttpStatus.UNPROCESSABLE_ENTITY.value(), message));
    }


    @ExceptionHandler(LicensePlateUniqueViolationException.class)
    public ResponseEntity<ErrorResponse> LicensePlateUniqueViolationException(RuntimeException  ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse( HttpStatus.CONFLICT.value(), ex.getMessage()));

    }


}
