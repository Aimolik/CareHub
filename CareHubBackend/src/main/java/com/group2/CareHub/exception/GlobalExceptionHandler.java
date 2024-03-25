package com.group2.CareHub.exception;

import com.group2.CareHub.exception.exceptions.EntityException;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityException.class)
    public String handleEntityPersistFailure(EntityException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public List<String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public List<String> handleConstraintViolation(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());
    }
}
