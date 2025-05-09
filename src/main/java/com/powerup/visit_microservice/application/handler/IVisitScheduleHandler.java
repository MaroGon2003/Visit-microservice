package com.powerup.visit_microservice.application.handler;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;

public interface IVisitScheduleHandler {

    void createVisitSchedule(VisitScheduleRequestDto visitScheduleRequestDto);

}
