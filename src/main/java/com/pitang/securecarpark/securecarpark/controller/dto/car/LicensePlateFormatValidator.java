package com.pitang.securecarpark.securecarpark.controller.dto.car;

import com.pitang.securecarpark.securecarpark.validation.LicensePlateFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LicensePlateFormatValidator implements ConstraintValidator<LicensePlateFormat, String> {

    private static final String LICENSE_PLATE_PATTERN = "[A-Z]{3}-\\d{4}";

    @Override
    public void initialize(LicensePlateFormat constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && Pattern.matches(LICENSE_PLATE_PATTERN, value);
    }
}
