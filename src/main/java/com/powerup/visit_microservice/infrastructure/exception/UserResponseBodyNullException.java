package com.powerup.visit_microservice.infrastructure.exception;

public class UserResponseBodyNullException extends RuntimeException {

    public UserResponseBodyNullException(String message) {
        super(message);
    }
}
