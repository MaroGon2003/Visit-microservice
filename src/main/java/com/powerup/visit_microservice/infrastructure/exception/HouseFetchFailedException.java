package com.powerup.visit_microservice.infrastructure.exception;

public class HouseFetchFailedException extends RuntimeException {

    public HouseFetchFailedException(String message) {
        super(message);
    }

}
