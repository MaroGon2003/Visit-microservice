package com.powerup.visit_microservice.domain.usecase;

import com.powerup.visit_microservice.domain.api.IVisitScheduleServicePort;
import com.powerup.visit_microservice.domain.exception.*;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.spi.IHouseFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IJwtInterceptorPort;
import com.powerup.visit_microservice.domain.spi.IUserFeignPersistencePort;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;
import com.powerup.visit_microservice.domain.utils.DomainConstants;

import java.time.LocalDateTime;

public class VisitScheduleUseCase implements IVisitScheduleServicePort {

    private final IVisitSchedulePersistencePort visitSchedulePersistencePort;
    private final IJwtInterceptorPort jwtInterceptorPort;
    private final IUserFeignPersistencePort userFeignPersistencePort;
    private final IHouseFeignPersistencePort houseFeignPersistencePort;

    public VisitScheduleUseCase(IVisitSchedulePersistencePort visitSchedulePersistencePort, IJwtInterceptorPort jwtInterceptorPort, IUserFeignPersistencePort userFeignPersistencePort, IHouseFeignPersistencePort houseFeignPersistencePort) {
        this.visitSchedulePersistencePort = visitSchedulePersistencePort;
        this.jwtInterceptorPort = jwtInterceptorPort;
        this.userFeignPersistencePort = userFeignPersistencePort;
        this.houseFeignPersistencePort = houseFeignPersistencePort;
    }


    @Override
    public void createVisitSchedule(VisitScheduleModel visitScheduleModel) {

        try {
            String email = jwtInterceptorPort.getEmailFromToken();
            Long userId = userFeignPersistencePort.getUserIdByEmail(email);
            visitScheduleModel.setSellerId(userId);
        }catch (Exception e){
            throw new UserNotFoundException(DomainConstants.USER_NOT_FOUND);
        }

        if (visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId())) {
            throw new VisitAlreadyScheduledForHouseException(DomainConstants.VISIT_ALREADY_SCHEDULED_FOR_HOUSE);
        }
        if (visitScheduleModel.getStartDateTime().isAfter(visitScheduleModel.getEndDateTime())) {
            throw new StartDateAfterEndDateException(DomainConstants.START_DATE_AFTER_END_DATE);
        }
        if (visitScheduleModel.getStartDateTime().isEqual(visitScheduleModel.getEndDateTime())) {
            throw new StartDateEqualsEndDateException(DomainConstants.START_DATE_EQUALS_END_DATE);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeWeeksFromNow = now.plusWeeks(3);

        if (visitScheduleModel.getStartDateTime().isAfter(threeWeeksFromNow) &&
                visitScheduleModel.getEndDateTime().isAfter(threeWeeksFromNow)) {
            throw new ScheduleOutOfRangeException(DomainConstants.SCHEDULE_OUT_OF_RANGE);
        }

        if (visitSchedulePersistencePort.existsBySellerIdAndDateRange(visitScheduleModel.getSellerId(),
                visitScheduleModel.getStartDateTime(), visitScheduleModel.getEndDateTime())) {
            throw new VisitAlreadyScheduledForDateRangeException(DomainConstants.VISIT_ALREADY_SCHEDULED_FOR_DATE_RANGE);
        }

        if(!houseFeignPersistencePort.existsHouseById(visitScheduleModel.getHouseId())){
            throw new HouseNotFoundException(DomainConstants.HOUSE_NOT_FOUND);
        }

       visitSchedulePersistencePort.createVisitSchedule(visitScheduleModel);

    }

}
