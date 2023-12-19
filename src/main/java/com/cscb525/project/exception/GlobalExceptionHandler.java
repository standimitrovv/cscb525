package com.cscb525.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        return exceptionHandling("Validation failed! " + e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;

        switch(e.getClass().getSimpleName()){
            case "CompanyVehicleNotFoundException",
                    "CompanyClientNotFoundException",
                    "CompanyEmployeeNotFoundException",
                    "CargoWeightNotDefinedException",
                    "CompanyRevenueNotFoundException",
                    "ShipmentPaymentStatusNotDefinedException",
                    "CompanyShipmentNotFoundException",
                    "TransportCompanyNotFoundException",
                    "VehicleNotFoundException",
                    "ClientNotFoundException",
                    "ShipmentNotFoundException",
                    "RevenueNotFoundException",
                    "EmployeeNotFoundException":
                status = HttpStatus.NOT_FOUND;
                break;
            case "ClientAlreadyExistsException",
                    "CompanyEmployeeAlreadyExistsException",
                    "EmployeeWorksForAnotherCompanyException":
                status = HttpStatus.CONFLICT;
                break;
        }


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
