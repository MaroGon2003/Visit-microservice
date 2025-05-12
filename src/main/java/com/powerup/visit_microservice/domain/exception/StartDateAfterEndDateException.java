package com.powerup.visit_microservice.domain.exception;

public class StartDateAfterEndDateException extends RuntimeException{

    public StartDateAfterEndDateException(String message){
        super(message);
    }

}
