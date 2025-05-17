package com.powerup.visit_microservice.domain.api;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;

import java.time.LocalDateTime;
import java.util.List;

public interface IVisitScheduleServicePort {

    void createVisitSchedule(VisitScheduleModel visitScheduleModel);
    List<VisitScheduleModel> getVisitSchedule(int page, int size, LocalDateTime startDateTime, LocalDateTime endDateTime);
    void createVisitRequest(Long visitScheduleId);
}
