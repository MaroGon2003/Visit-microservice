package com.powerup.visit_microservice.domain.exception;

public class VisitAlreadyRequestedException extends RuntimeException {

    public VisitAlreadyRequestedException(String message) {
        super(message);
    }

}
