package com.powerup.visit_microservice.infrastructure.out.jpa.mapper;

import com.powerup.visit_microservice.domain.model.VisitScheduleRequestModel;
import com.powerup.visit_microservice.infrastructure.out.jpa.entity.VisitScheduleRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVisitScheduleRequestEntityMapper {

    VisitScheduleRequestEntity toVisitScheduleRequestEntity(VisitScheduleRequestModel visitScheduleRequestModel);

}
