package com.moweez.moweez_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions", indexes = {
        @Index(name = "idx_subscriptions_customer_id", columnList = "customer_id"),
        @Index(name = "idx_subscriptions_type", columnList = "type"),
        @Index(name = "idx_subscriptions_active", columnList = "active")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Subscription type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private SubscriptionType type;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Positive(message = "Price must be positive")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "active", nullable = false)
    private boolean active;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    // Additional fields for analytics

    @PositiveOrZero(message = "Total jobs must be zero or positive")
    @Column(name = "total_jobs", nullable = false)
    private int totalJobs;

    @PositiveOrZero(message = "Total amount saved must be zero or positive")
    @Column(name = "total_amount_saved", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmountSaved;

    @PositiveOrZero(message = "Total amount spent must be zero or positive")
    @Column(name = "total_amount_spent", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmountSpent;
}
