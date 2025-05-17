package com.powerup.visit_microservice.domain.factory;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.model.VisitScheduleRequestModel;

import java.time.LocalDateTime;

public class VisitScheduleTestDataFactory {

    public static VisitScheduleModel visitScheduleModelWithNullSellerId() {
        VisitScheduleModel visitScheduleModel = new VisitScheduleModel();
        visitScheduleModel.setSellerId(null);
        visitScheduleModel.setHouseId(1L);
        visitScheduleModel.setStartDateTime(LocalDateTime.now().plusWeeks(1));
        visitScheduleModel.setEndDateTime(LocalDateTime.now().plusWeeks(1).plusHours(1));
        visitScheduleModel.setAvailable(true);
        return visitScheduleModel;
    }

    public static VisitScheduleModel validVisitScheduleModel() {
        return new VisitScheduleModel(1L,1L, 1L, LocalDateTime.now().plusWeeks(1), LocalDateTime.now().plusWeeks(1).plusHours(1), true);
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

    public static VisitScheduleRequestModel visitScheduleRequestModeWithNullBuyerEmail() {
        VisitScheduleRequestModel visitScheduleRequestModel = new VisitScheduleRequestModel();
        visitScheduleRequestModel.setId(1L);
        visitScheduleRequestModel.setVisitScheduleId(1L);
        visitScheduleRequestModel.setBuyerEmail("email@hotmail.com");
        return visitScheduleRequestModel;
    }

}
