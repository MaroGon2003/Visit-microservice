package com.powerup.visit_microservice.infrastructure.out.jpa.repository;

import com.powerup.visit_microservice.infrastructure.out.jpa.entity.VisitScheduleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface IVisitScheduleRepository extends JpaRepository<VisitScheduleEntity, Long> {
    boolean existsBySellerIdAndHouseId(Long sellerId, Long houseId);

    boolean existsBySellerIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(Long sellerId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<VisitScheduleEntity> findAllByEndDateTimeLessThanAndIsAvailableTrue(LocalDateTime endDateTime, Pageable pageable);

    List<VisitScheduleEntity> findAllByStartDateTimeGreaterThanAndIsAvailableTrue(LocalDateTime startDateTime, Pageable pageable);
    @Query("SELECT v FROM VisitScheduleEntity v WHERE " +
            "(v.startDateTime >= :startDateTime) AND " +
            "(v.endDateTime <= :endDateTime) AND " +
            "(v.isAvailable = true)")
    List<VisitScheduleEntity> findAllInRange(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, Pageable pageable);
}
