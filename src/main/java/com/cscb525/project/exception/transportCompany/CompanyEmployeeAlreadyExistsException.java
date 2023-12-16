package com.cscb525.project.exception.transportCompany;

public class CompanyEmployeeAlreadyExistsException extends RuntimeException{
    public CompanyEmployeeAlreadyExistsException(String message){
        super(message);
    }
}
