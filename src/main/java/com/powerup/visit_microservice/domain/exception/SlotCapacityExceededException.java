package com.powerup.visit_microservice.domain.exception;

public class SlotCapacityExceededException extends RuntimeException {

    public SlotCapacityExceededException(String message) {
        super(message);
    }

}
