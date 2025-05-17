package com.powerup.visit_microservice.application.mapper.response;

import com.powerup.visit_microservice.application.dto.response.VisitScheduleResponseDto;
import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVisitScheduleResponseMapper {

    VisitScheduleResponseDto toVisitScheduleResponseDto(VisitScheduleModel visitScheduleModel);

    List<VisitScheduleResponseDto> toVisitScheduleResponseDtoList(List<VisitScheduleModel> visitScheduleModels);

}
