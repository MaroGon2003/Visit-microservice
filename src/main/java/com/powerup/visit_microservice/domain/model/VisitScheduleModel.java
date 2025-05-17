package com.powerup.visit_microservice.domain.model;

import java.time.LocalDateTime;

public class VisitScheduleModel {

    private Long id;
    private Long sellerId;
    private Long houseId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean isAvailable;

    public VisitScheduleModel() {
    }

    public VisitScheduleModel(Long id, Long sellerId, Long houseId, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isAvailable) {
        this.id = id;
        this.sellerId = sellerId;
        this.houseId = houseId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
