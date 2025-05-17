package com.powerup.visit_microservice.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "visit_schedule_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisitScheduleRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_schedule_id", nullable = false)
    private Long visitScheduleId;

    @Column(name = "buyer_email", nullable = false)
    private String buyerEmail;

}
