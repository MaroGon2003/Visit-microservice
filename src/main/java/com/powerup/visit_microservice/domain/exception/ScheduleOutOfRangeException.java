package com.powerup.visit_microservice.domain.exception;

public class ScheduleOutOfRangeException extends RuntimeException{

    public ScheduleOutOfRangeException(String message){
        super(message);
    }

}
