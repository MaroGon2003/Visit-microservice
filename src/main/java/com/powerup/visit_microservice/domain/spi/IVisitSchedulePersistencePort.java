package com.powerup.visit_microservice.domain.spi;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;

import java.time.LocalDateTime;

public interface IVisitSchedulePersistencePort {

    void createVisitSchedule(VisitScheduleModel visitScheduleModel);

    boolean existsBySellerIdAndHouseId(Long sellerId, Long houseId);

    boolean existsBySellerIdAndDateRange(Long sellerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
