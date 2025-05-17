package com.powerup.visit_microservice.domain.exception;

public class VisitScheduleNotFoundException extends RuntimeException {

    public VisitScheduleNotFoundException(String message) {
        super(message);
    }
}
