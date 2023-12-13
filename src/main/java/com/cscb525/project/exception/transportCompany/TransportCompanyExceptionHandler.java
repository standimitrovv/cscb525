package com.cscb525.project.exception.transportCompany;

import com.cscb525.project.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransportCompanyExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        HttpStatus status = HttpStatus.NOT_IMPLEMENTED;

        switch(e.getClass().getSimpleName()){
            case "CompanyVehicleNotFoundException",
                    "CompanyClientNotFoundException",
                    "CompanyEmployeeNotFoundException",
                    "CargoWeightNotDefinedException",
                    "ShipmentPaymentStatusNotDefined",
                    "CompanyShipmentNotFound":
                status = HttpStatus.NOT_FOUND;
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
