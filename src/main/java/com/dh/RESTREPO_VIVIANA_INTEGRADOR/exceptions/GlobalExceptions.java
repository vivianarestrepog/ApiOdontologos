package com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> tratamientoErrorResourceNotFound(ResourceNotFoundException resourceNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atención: Error. " + resourceNotFoundException.getMessage()+ "\n" + resourceNotFoundException.getStackTrace());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestException.getMessage());
    }
}
