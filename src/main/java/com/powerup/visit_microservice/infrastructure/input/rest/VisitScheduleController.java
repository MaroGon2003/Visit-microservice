package com.powerup.visit_microservice.infrastructure.input.rest;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.handler.IVisitScheduleHandler;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(InfrastructureConstants.VISIT_SCHEDULES_BASE_PATH)
@Tag(name = InfrastructureConstants.TAG_NAME, description = InfrastructureConstants.TAG_DESCRIPTION)
public class VisitScheduleController {

    private final IVisitScheduleHandler visitScheduleHandler;

    public VisitScheduleController(IVisitScheduleHandler visitScheduleHandler) {
        this.visitScheduleHandler = visitScheduleHandler;
    }

    @Operation(
            summary = InfrastructureConstants.CREATE_VISIT_SCHEDULE_SUMMARY,
            description = InfrastructureConstants.CREATE_VISIT_SCHEDULE_DESCRIPTION
    )@ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.RESPONSE_201_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.RESPONSE_400_DESCRIPTION),
    })
    @PostMapping
    public ResponseEntity<Void> createVisitSchedule(@RequestBody @Valid VisitScheduleRequestDto visitScheduleRequestDto) {
        visitScheduleHandler.createVisitSchedule(visitScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
