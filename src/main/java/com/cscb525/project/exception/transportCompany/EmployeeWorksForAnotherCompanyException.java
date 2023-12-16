package com.cscb525.project.exception.transportCompany;

public class EmployeeWorksForAnotherCompanyException extends RuntimeException{
    public EmployeeWorksForAnotherCompanyException(String message){
        super(message);
    }
}
