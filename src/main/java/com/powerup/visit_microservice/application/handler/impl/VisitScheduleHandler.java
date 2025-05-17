package com.powerup.visit_microservice.application.handler.impl;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.dto.response.VisitScheduleResponseDto;
import com.powerup.visit_microservice.application.handler.IVisitScheduleHandler;
import com.powerup.visit_microservice.application.mapper.request.IVisitScheduleRequestMapper;
import com.powerup.visit_microservice.application.mapper.response.IVisitScheduleResponseMapper;
import com.powerup.visit_microservice.application.utils.PagedResult;
import com.powerup.visit_microservice.domain.api.IVisitScheduleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitScheduleHandler implements IVisitScheduleHandler {

    private final IVisitScheduleRequestMapper visitScheduleRequestMapper;
    private final IVisitScheduleServicePort visitScheduleServicePort;
    private final IVisitScheduleResponseMapper visitScheduleResponseMapper;
    @Override
    public void createVisitSchedule(VisitScheduleRequestDto visitScheduleRequestDto) {

        visitScheduleServicePort.createVisitSchedule(visitScheduleRequestMapper.toVisitScheduleModel(visitScheduleRequestDto));

    }

    @Override
    public PagedResult<VisitScheduleResponseDto> getVisitSchedule(int page, int size, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<VisitScheduleResponseDto> visitScheduleResponseDtoList = visitScheduleResponseMapper.toVisitScheduleResponseDtoList(visitScheduleServicePort.getVisitSchedule(page, size, startDateTime, endDateTime));

        return new PagedResult<>(visitScheduleResponseDtoList, page, size);
    }

    @Override
    public void createVisitRequest(Long visitScheduleId) {
        visitScheduleServicePort.createVisitRequest(visitScheduleId);
    }

}
