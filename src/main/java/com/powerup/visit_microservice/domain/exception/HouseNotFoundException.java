package com.powerup.visit_microservice.domain.exception;

public class HouseNotFoundException extends RuntimeException{

    public HouseNotFoundException(String message) {
        super(message);
    }

}
