package com.joaquin.ClinicaMVC.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmailAlreadyRegistered.class)
    public ResponseEntity<String> handleEmailAlreadyRegistered(EmailAlreadyRegistered exeption){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exeption.getMessage());
    }
}
