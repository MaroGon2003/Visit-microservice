package com.powerup.visit_microservice.application.mapper.request;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVisitScheduleRequestMapper {

    VisitScheduleModel toVisitScheduleModel(VisitScheduleRequestDto visitScheduleRequestDto);

}
