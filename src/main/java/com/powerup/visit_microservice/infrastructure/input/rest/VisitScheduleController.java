package com.powerup.visit_microservice.infrastructure.input.rest;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.handler.IVisitScheduleHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: hacer documentacion de controller y enpoints
@RestController
@RequestMapping("/visit-schedules")
public class VisitScheduleController {

    private final IVisitScheduleHandler visitScheduleHandler;

    public VisitScheduleController(IVisitScheduleHandler visitScheduleHandler) {
        this.visitScheduleHandler = visitScheduleHandler;
    }

    public ResponseEntity<Void>  createVisitSchedule(@RequestBody @Valid VisitScheduleRequestDto visitScheduleRequestDto) {
        visitScheduleHandler.createVisitSchedule(visitScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
