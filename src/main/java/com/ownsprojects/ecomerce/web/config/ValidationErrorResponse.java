package com.ownsprojects.ecomerce.web.config;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

// ...

public class ValidationErrorResponse {
    private List<String> errors;

    public ValidationErrorResponse(List<ObjectError> errors) {
        this.errors = errors
                .stream()
                .map(this::getErrorMessage) // Cambio en esta línea
                .collect(Collectors.toList());
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            return ((FieldError) error).getDefaultMessage();
        }
        return error.getDefaultMessage();
    }

    public List<String> getErrors() {
        return errors;
    }
}