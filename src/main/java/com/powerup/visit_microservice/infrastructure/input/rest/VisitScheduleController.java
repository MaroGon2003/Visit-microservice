package com.powerup.visit_microservice.infrastructure.input.rest;

import com.powerup.visit_microservice.application.dto.request.VisitScheduleRequestDto;
import com.powerup.visit_microservice.application.dto.response.VisitScheduleResponseDto;
import com.powerup.visit_microservice.application.handler.IVisitScheduleHandler;
import com.powerup.visit_microservice.application.utils.PagedResult;
import com.powerup.visit_microservice.infrastructure.utils.InfrastructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @PostMapping("/save")
    public ResponseEntity<Void> createVisitSchedule(@RequestBody @Valid VisitScheduleRequestDto visitScheduleRequestDto) {
        visitScheduleHandler.createVisitSchedule(visitScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = InfrastructureConstants.GET_VISIT_SCHEDULES_SUMMARY,
            description = InfrastructureConstants.GET_VISIT_SCHEDULES_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_200, description = InfrastructureConstants.RESPONSE_CODE_200_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.RESPONSE_CODE_400_DESCRIPTION),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.RESPONSE_CODE_500_DESCRIPTION)
    })
    @GetMapping
    public ResponseEntity<PagedResult<VisitScheduleResponseDto>> getVisitSchedule(@RequestParam(defaultValue = InfrastructureConstants.DEFAULT_PAGE) int page,
                                                                                                                                          @RequestParam(defaultValue = InfrastructureConstants.DEFAULT_SIZE) int size,
                                                                                                                                          @RequestParam(required = false) LocalDateTime startDateTime,
                                                                                                                                          @RequestParam(required = false) LocalDateTime endDateTime) {
        PagedResult<VisitScheduleResponseDto> result = visitScheduleHandler.getVisitSchedule(page, size, startDateTime, endDateTime);
        return ResponseEntity.ok(result);
    }


    @Operation(
            summary = InfrastructureConstants.CREATE_VISIT_REQUEST_SUMMARY,
            description = InfrastructureConstants.CREATE_VISIT_REQUEST_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_201, description = InfrastructureConstants.RESPONSE_201_VISIT_REQUEST_CREATED),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_400, description = InfrastructureConstants.RESPONSE_400_INVALID_INPUT),
            @ApiResponse(responseCode = InfrastructureConstants.RESPONSE_CODE_500, description = InfrastructureConstants.RESPONSE_500_INTERNAL_SERVER_ERROR)
    })
    @PostMapping(InfrastructureConstants.VISIT_SCHEDULES_REQUEST)
    public ResponseEntity<Void> createVisitRequest(@RequestParam Long visitScheduleId) {
        visitScheduleHandler.createVisitRequest(visitScheduleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
