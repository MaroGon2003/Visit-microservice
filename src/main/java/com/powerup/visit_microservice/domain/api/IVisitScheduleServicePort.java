package com.powerup.visit_microservice.domain.api;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;

public interface IVisitScheduleServicePort {

    void createVisitSchedule(VisitScheduleModel visitScheduleModel);

}
