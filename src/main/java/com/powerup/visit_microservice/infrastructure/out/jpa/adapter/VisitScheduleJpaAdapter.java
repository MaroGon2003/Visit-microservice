package com.powerup.visit_microservice.infrastructure.out.jpa.adapter;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.infrastructure.out.jpa.mapper.IVisitScheduleEntityMapper;
import com.powerup.visit_microservice.infrastructure.out.jpa.repository.IVisitScheduleRepository;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import com.powerup.visit_microservice.infrastructure.utils.PaginationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<List<VisitScheduleModel>> getVisitSchedulesByEndDateTime(LocalDateTime endDateTime, int page, int size) {

        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_START_DATE_TIME, InfrastructureConstants.SORT_DIRECTION_DESC);

        return Optional.of(visitScheduleEntityMapper.toVisitScheduleModelList(
                visitScheduleRepository.findAllByEndDateTimeLessThanAndIsAvailableTrue(endDateTime,pageable)));
    }

    @Override
    public Optional<List<VisitScheduleModel>> getVisitSchedulesByStartDateTime(LocalDateTime startDateTime, int page, int size) {

        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_START_DATE_TIME, InfrastructureConstants.SORT_DIRECTION_DESC);

        return Optional.of(visitScheduleEntityMapper.toVisitScheduleModelList(
                visitScheduleRepository.findAllByStartDateTimeGreaterThanAndIsAvailableTrue(startDateTime,pageable)));
    }

    @Override
    public Optional<List<VisitScheduleModel>> getVisitSchedulesByDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime, int page, int size) {

        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_START_DATE_TIME, InfrastructureConstants.SORT_DIRECTION_DESC);

        return Optional.of(visitScheduleEntityMapper.toVisitScheduleModelList(
                visitScheduleRepository.findAllInRange(startDateTime, endDateTime, pageable)));
    }

    @Override
    public Optional<List<VisitScheduleModel>> getAllVisitSchedules(int page, int size) {

        Pageable pageable = PaginationUtils.createPageable(page, size, InfrastructureConstants.SORT_BY_START_DATE_TIME, InfrastructureConstants.SORT_DIRECTION_DESC);

        return Optional.of(visitScheduleEntityMapper.toVisitScheduleModelList(
                visitScheduleRepository.findAll(pageable).toList()));
    }

}
