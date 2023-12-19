package com.cscb525.project.exception.client;

import com.cscb525.project.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        return exceptionHandling("Validation failed! " + e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;

        return exceptionHandling(e.getMessage(), status);
    }

    private ResponseEntity<ErrorResponse> exceptionHandling(String message, HttpStatus status){
        ErrorResponse err = new ErrorResponse();

        err.setMessage(message);
        err.setStatus(status.value());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, status);
    }
}
