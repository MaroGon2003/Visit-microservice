package com.powerup.visit_microservice.domain.exception;

public class VisitAlreadyScheduledForDateRangeException extends RuntimeException {

    public VisitAlreadyScheduledForDateRangeException(String message) {
        super(message);
    }

}
