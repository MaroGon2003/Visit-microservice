package com.powerup.visit_microservice.infrastructure.out.jpa.mapper;

import com.powerup.visit_microservice.domain.model.VisitScheduleModel;
import com.powerup.visit_microservice.infrastructure.out.jpa.entity.VisitScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVisitScheduleEntityMapper {

    VisitScheduleEntity toVisitScheduleEntity(VisitScheduleModel visitScheduleModel);

    VisitScheduleModel toVisitScheduleModel(VisitScheduleEntity visitScheduleEntity);

    List<VisitScheduleModel> toVisitScheduleModelList(List<VisitScheduleEntity> visitScheduleEntities);

}
