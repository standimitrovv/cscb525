package com.cscb525.project.exception.revenue;

public class RevenueNotFoundException extends RuntimeException{
    public RevenueNotFoundException(String message){
        super(message);
    }
}
