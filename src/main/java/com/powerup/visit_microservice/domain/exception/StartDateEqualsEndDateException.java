package com.powerup.visit_microservice.domain.exception;

public class StartDateEqualsEndDateException  extends RuntimeException{

    public StartDateEqualsEndDateException(String message){
        super(message);
    }
}
