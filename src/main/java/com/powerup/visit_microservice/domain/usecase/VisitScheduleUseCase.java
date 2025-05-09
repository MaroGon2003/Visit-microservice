package com.powerup.visit_microservice.domain.usecase;

import com.powerup.visit_microservice.domain.api.IVisitScheduleServicePort;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.domain.spi.IVisitSchedulePersistencePort;

import java.time.LocalDateTime;

public class VisitScheduleUseCase implements IVisitScheduleServicePort {

    private final IVisitSchedulePersistencePort visitSchedulePersistencePort;

    public VisitScheduleUseCase(IVisitSchedulePersistencePort visitSchedulePersistencePort) {
        this.visitSchedulePersistencePort = visitSchedulePersistencePort;
    }

    @Override
    public void createVisitSchedule(VisitScheduleModel visitScheduleModel) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeWeeksFromNow = now.plusWeeks(3);

        if (visitScheduleModel.getStartDateTime().isBefore(now) ||
                visitScheduleModel.getEndDateTime().isAfter(threeWeeksFromNow)) {
            throw new IllegalArgumentException("El horario debe estar dentro de las próximas 3 semanas.");
        }

        if(visitScheduleModel.getStartDateTime().isAfter(visitScheduleModel.getEndDateTime())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        if (visitScheduleModel.getStartDateTime().isEqual(visitScheduleModel.getEndDateTime())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser igual a la fecha de fin.");
        }

        if(visitSchedulePersistencePort.existsBySellerIdAndHouseId(visitScheduleModel.getSellerId(), visitScheduleModel.getHouseId())) {
            throw new IllegalArgumentException("Ya existe una visita programada para esta casa y vendedor.");
        }

        if (visitSchedulePersistencePort.existsBySellerIdAndDateRange(visitScheduleModel.getSellerId(),
                visitScheduleModel.getStartDateTime(), visitScheduleModel.getEndDateTime())) {
            throw new IllegalArgumentException("El vendedor ya tiene una visita programada en este rango de fechas.");
        }

        //TODO: traer el id del vendedor atravex de un feing

        //TODO: validar que el id de la casa exista atravez de un feing



    }

}
