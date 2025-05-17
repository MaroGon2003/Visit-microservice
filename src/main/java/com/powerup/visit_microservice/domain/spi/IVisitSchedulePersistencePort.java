package com.powerup.visit_microservice.domain.spi;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.model.VisitScheduleRequestModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IVisitSchedulePersistencePort {

    void createVisitSchedule(VisitScheduleModel visitScheduleModel);
    boolean existsBySellerIdAndHouseId(Long sellerId, Long houseId);
    boolean existsBySellerIdAndDateRange(Long sellerId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    Optional<List<VisitScheduleModel>> getVisitSchedulesByEndDateTime(LocalDateTime endDateTime, int page, int size);
    Optional<List<VisitScheduleModel>> getVisitSchedulesByStartDateTime(LocalDateTime startDateTime, int page, int size);
    Optional<List<VisitScheduleModel>> getVisitSchedulesByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime, int page, int size);
    Optional<List<VisitScheduleModel>> getAllVisitSchedules(int page, int size);
    void createVisitRequest(VisitScheduleRequestModel visitScheduleRequestModel);
    boolean existsByVisitScheduleIdAndBuyerEmail(Long visitScheduleId, String buyerEmail);
    boolean existsByVisitScheduleId(Long visitScheduleId);
    int validateSlotCapacity(Long visitScheduleId);
    VisitScheduleModel getVisitScheduleById(Long visitScheduleId);
}
