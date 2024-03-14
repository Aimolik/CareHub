package com.group2.CareHub.exception;

import com.group2.CareHub.exception.exceptions.EntityException;
import com.group2.CareHub.exception.exceptions.EntityPersistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityException.class)
    public String handleEntityPersistFailure(EntityException ex) {
        return ex.getMessage();
    }

}
