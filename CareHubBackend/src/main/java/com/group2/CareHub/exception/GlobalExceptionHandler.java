package com.group2.CareHub.exception;

import com.group2.CareHub.exception.exceptions.EntityPersistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityPersistException.class)
    public String handleEntityPersistFailure(EntityPersistException ex) {
        return ex.getMessage();
    }
}
