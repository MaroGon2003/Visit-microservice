package com.powerup.visit_microservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitScheduleResponseDto {

    private Long id;
    private Long sellerId;
    private Long houseId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
