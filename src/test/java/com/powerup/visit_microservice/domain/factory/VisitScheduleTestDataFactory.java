package com.powerup.visit_microservice.domain.factory;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;

import java.time.LocalDateTime;

public class VisitScheduleTestDataFactory {

    public static VisitScheduleModel visitScheduleModelWithNullSellerId() {
        VisitScheduleModel visitScheduleModel = new VisitScheduleModel();
        visitScheduleModel.setSellerId(null);
        visitScheduleModel.setHouseId(1L);
        visitScheduleModel.setStartDateTime(LocalDateTime.now().plusWeeks(1));
        visitScheduleModel.setEndDateTime(LocalDateTime.now().plusWeeks(1).plusHours(1));
        return visitScheduleModel;
    }

    public static VisitScheduleModel validVisitScheduleModel() {
        return new VisitScheduleModel(1L,1L, 1L, LocalDateTime.now().plusWeeks(1), LocalDateTime.now().plusWeeks(1).plusHours(1));
    }

    public static VisitScheduleModel visitScheduleModelWithStartDateAfterEndDate() {
        VisitScheduleModel visitScheduleModel = new VisitScheduleModel();
        visitScheduleModel.setSellerId(1L);
        visitScheduleModel.setHouseId(1L);
        visitScheduleModel.setStartDateTime(LocalDateTime.now().plusWeeks(2).plusHours(1));
        visitScheduleModel.setEndDateTime(LocalDateTime.now().plusWeeks(1));
        return visitScheduleModel;
    }

    public static VisitScheduleModel visitScheduleModelWithStartDateEqualsEndDate() {
        VisitScheduleModel visitScheduleModel = new VisitScheduleModel();
        visitScheduleModel.setSellerId(1L);
        visitScheduleModel.setHouseId(1L);
        LocalDateTime dateTime = LocalDateTime.now().plusWeeks(1);
        visitScheduleModel.setStartDateTime(dateTime);
        visitScheduleModel.setEndDateTime(dateTime);
        return visitScheduleModel;
    }

}
