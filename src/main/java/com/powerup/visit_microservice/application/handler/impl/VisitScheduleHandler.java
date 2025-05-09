package com.powerup.visit_microservice.application.handler.impl;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.handler.IVisitScheduleHandler;
import com.powerup.visit_microservice.application.mapper.request.IVisitScheduleRequestMapper;
import com.powerup.visit_microservice.domain.api.IVisitScheduleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitScheduleHandler implements IVisitScheduleHandler {

    private final IVisitScheduleRequestMapper visitScheduleRequestMapper;
    private final IVisitScheduleServicePort visitScheduleServicePort;
    @Override
    public void createVisitSchedule(VisitScheduleRequestDto visitScheduleRequestDto) {

        visitScheduleServicePort.createVisitSchedule(visitScheduleRequestMapper.toVisitScheduleModel(visitScheduleRequestDto));

    }
}
