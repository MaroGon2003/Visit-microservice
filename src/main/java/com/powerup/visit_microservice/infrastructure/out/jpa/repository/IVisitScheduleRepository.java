package com.powerup.visit_microservice.infrastructure.out.jpa.repository;

import com.powerup.visit_microservice.infrastructure.out.jpa.entity.VisitScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IVisitScheduleRepository extends JpaRepository<VisitScheduleEntity, Long> {
    boolean existsBySellerIdAndHouseId(Long sellerId, Long houseId);

    boolean existsBySellerIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(Long sellerId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
