package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionNotFoundHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> error(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
                "Not found object type " +
                        exception.getResourceName() +
                        "finding by" +
                        exception.getFieldName() +
                        "using value: " +
                        exception.getFieldValue(),
                HttpStatus.BAD_REQUEST);
    }
}