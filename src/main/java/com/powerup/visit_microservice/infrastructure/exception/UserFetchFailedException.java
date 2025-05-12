package com.powerup.visit_microservice.infrastructure.exception;

public class UserFetchFailedException extends RuntimeException {

    public UserFetchFailedException(String message) {
        super(message);
    }

}
