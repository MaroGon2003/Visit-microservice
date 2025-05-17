package com.powerup.visit_microservice.application.handler;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.dto.response.VisitScheduleResponseDto;
import com.powerup.visit_microservice.application.utils.PagedResult;

import java.time.LocalDateTime;

public interface IVisitScheduleHandler {

    void createVisitSchedule(VisitScheduleRequestDto visitScheduleRequestDto);

    PagedResult<VisitScheduleResponseDto> getVisitSchedule(int page, int size, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
