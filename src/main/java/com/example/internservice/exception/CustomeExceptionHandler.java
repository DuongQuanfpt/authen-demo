package com.example.internservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.internservice.exception.custom.CustomNotFoundException;

@RestControllerAdvice
public class CustomeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAllException(Exception ex, WebRequest request) {
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String customNotfoundHandlerString(Exception ex, WebRequest request) {
        if(ex.getLocalizedMessage() != null) {
            return ex.getLocalizedMessage();
        }
        return "Intern not found";
    }

}
