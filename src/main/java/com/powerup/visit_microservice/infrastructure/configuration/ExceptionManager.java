package com.powerup.visit_microservice.infrastructure.configuration;

import com.powerup.visit_microservice.domain.exception.*;
import com.powerup.visit_microservice.infrastructure.exception.HouseFetchFailedException;
import com.powerup.visit_microservice.infrastructure.exception.UserFetchFailedException;
import com.powerup.visit_microservice.infrastructure.exception.UserResponseBodyNullException;
import com.powerup.visit_microservice.infrastructure.utils.ApiResponse;
import com.powerup.visit_microservice.infrastructure.exception.HouseResponseBodyNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> illegalArgumentException(IllegalArgumentException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<ApiResponse> houseNotFoundException(HouseNotFoundException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleOutOfRangeException.class)
    public ResponseEntity<ApiResponse> scheduleOutOfRangeException(ScheduleOutOfRangeException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StartDateAfterEndDateException.class)
    public ResponseEntity<ApiResponse> startDateAfterEndDateException(StartDateAfterEndDateException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StartDateEqualsEndDateException.class)
    public ResponseEntity<ApiResponse> startDateEqualsEndDateException(StartDateEqualsEndDateException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VisitAlreadyScheduledForDateRangeException.class)
    public ResponseEntity<ApiResponse> visitAlreadyScheduledForDateRangeException(VisitAlreadyScheduledForDateRangeException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VisitAlreadyScheduledForHouseException.class)
    public ResponseEntity<ApiResponse> visitAlreadyScheduledForHouseException(VisitAlreadyScheduledForHouseException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> userNotFoundException(UserNotFoundException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HouseFetchFailedException.class)
    public ResponseEntity<ApiResponse> houseFetchFailedException(HouseFetchFailedException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HouseResponseBodyNullException.class)
    public ResponseEntity<ApiResponse> houseResponseBodyNullException(HouseResponseBodyNullException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserFetchFailedException.class)
    public ResponseEntity<ApiResponse> userFetchFailedException(UserFetchFailedException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserResponseBodyNullException.class)
    public ResponseEntity<ApiResponse> userResponseBodyNullException(UserResponseBodyNullException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SlotCapacityExceededException.class)
    public ResponseEntity<ApiResponse> slotCapacityExceededException(SlotCapacityExceededException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VisitAlreadyRequestedException.class)
    public ResponseEntity<ApiResponse> visitAlreadyRequestedException(VisitAlreadyRequestedException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VisitScheduleNotFoundException.class)
    public ResponseEntity<ApiResponse> visitScheduleNotFoundException(VisitScheduleNotFoundException e, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse(e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {

        Map<String, String> mapErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            mapErrors.put(field, message);
        });

        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), request.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
