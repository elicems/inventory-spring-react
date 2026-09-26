package com.crud.training.controller.exception;

import com.crud.training.services.exceptions.DatabaseException;
import com.crud.training.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ObjectNotFoundHandler{
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest http){
        String errorMsg = "Object not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),errorMsg,e.getMessage(),http.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    public ResponseEntity<StandardError> databaseException(DatabaseException e,HttpServletRequest http){
        String errorMsg = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(),status.value(),errorMsg,e.getMessage(),http.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


}
