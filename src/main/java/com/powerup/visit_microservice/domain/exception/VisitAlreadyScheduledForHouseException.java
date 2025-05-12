package com.powerup.visit_microservice.domain.exception;

public class VisitAlreadyScheduledForHouseException extends RuntimeException {

    public VisitAlreadyScheduledForHouseException(String message) {
        super(message);
    }
}
