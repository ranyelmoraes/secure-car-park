package com.pitang.securecarpark.securecarpark.validation;

import com.pitang.securecarpark.securecarpark.controller.dto.car.LicensePlateFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LicensePlateFormatValidator.class)
public @interface LicensePlateFormat {
    String message() default "Invalid fields (license plate)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}