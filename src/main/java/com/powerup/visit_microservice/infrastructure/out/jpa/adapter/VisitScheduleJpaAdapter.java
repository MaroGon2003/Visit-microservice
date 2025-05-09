package com.powerup.visit_microservice.infrastructure.out.jpa.adapter;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.infrastructure.out.jpa.mapper.IVisitScheduleEntityMapper;
import com.powerup.visit_microservice.infrastructure.out.jpa.repository.IVisitScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
public class VisitScheduleJpaAdapter implements IVisitSchedulePersistencePort {

    private final IVisitScheduleEntityMapper visitScheduleEntityMapper;

    private final IVisitScheduleRepository visitScheduleRepository;

    @Override
    public void createVisitSchedule(VisitScheduleModel visitScheduleModel) {

        visitScheduleRepository.save(visitScheduleEntityMapper.toVisitScheduleEntity(visitScheduleModel));

    }

    @Override
    public boolean existsBySellerIdAndHouseId(Long sellerId, Long houseId) {
        return visitScheduleRepository.existsBySellerIdAndHouseId(sellerId, houseId);
    }

    @Override
    public boolean existsBySellerIdAndDateRange(Long sellerId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return visitScheduleRepository.existsBySellerIdAndStartDateTimeLessThanEqualAndEndDateTimeGreaterThanEqual(
                sellerId, startDateTime, endDateTime);
    }
}
