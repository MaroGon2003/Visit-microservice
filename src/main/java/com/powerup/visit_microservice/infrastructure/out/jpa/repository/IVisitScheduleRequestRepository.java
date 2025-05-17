package com.powerup.visit_microservice.infrastructure.out.jpa.repository;

import com.powerup.visit_microservice.infrastructure.out.jpa.entity.VisitScheduleRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitScheduleRequestRepository extends JpaRepository<VisitScheduleRequestEntity, Long> {
    boolean existsByVisitScheduleIdAndBuyerEmail(Long visitScheduleId, String buyerEmail);
    int countByVisitScheduleId(Long visitScheduleId);
}
