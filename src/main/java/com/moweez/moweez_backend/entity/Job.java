package com.moweez.moweez_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import lombok.*;

@Data
public class Job {
    @Id
    private String id;
    private String customerId;
    private String driverId;
    private LocalDateTime jobDateTime;
    private JobStatus status;
    private String address;
    private double lawnSizeSqFt;
    private BigDecimal price;
    private String notes;
    private Double latitude;
    private Double longitude;

}
