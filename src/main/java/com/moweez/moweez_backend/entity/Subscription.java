package com.moweez.moweez_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Subscription {
    @Id
    private String id;
    private String customerId;
    private SubscriptionType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private boolean active;

}
