package com.powerup.visit_microservice.domain.model;

public class VisitScheduleRequestModel {

    private Long id;
    private Long visitScheduleId;
    private String buyerEmail;

    public VisitScheduleRequestModel() {
    }

    public VisitScheduleRequestModel(Long id, Long visitScheduleId, String buyerEmail) {
        this.id = id;
        this.visitScheduleId = visitScheduleId;
        this.buyerEmail = buyerEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitScheduleId() {
        return visitScheduleId;
    }

    public void setVisitScheduleId(Long visitScheduleId) {
        this.visitScheduleId = visitScheduleId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
