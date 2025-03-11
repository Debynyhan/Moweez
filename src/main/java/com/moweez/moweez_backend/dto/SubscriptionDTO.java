package com.moweez.moweez_backend.dto;

import com.moweez.moweez_backend.entity.SubscriptionType;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Subscription information.
 * <p>
 * This DTO is used to transfer subscription data between the backend and the client,
 * ensuring that only non-sensitive fields are exposed. It includes analytics-related fields
 * such as total jobs, total amount saved, and total amount spent.
 * </p>
 */
@Data
@Builder
public class SubscriptionDTO {

    private final Long id;

    @NotNull(message = "Customer ID is required")
    private final Long customerId;

    @NotNull(message = "Subscription type is required")
    private final SubscriptionType type;

    @NotNull(message = "Start date is required")
    private final LocalDateTime startDate;

    @NotNull(message = "End date is required")
    private final LocalDateTime endDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private final BigDecimal price;

    private final boolean active;

    private final LocalDateTime createdDate;

    private final LocalDateTime lastModifiedDate;

    @PositiveOrZero(message = "Total jobs must be zero or positive")
    private final int totalJobs;

    @PositiveOrZero(message = "Total amount saved must be zero or positive")
    private final BigDecimal totalAmountSaved;

    @PositiveOrZero(message = "Total amount spent must be zero or positive")
    private final BigDecimal totalAmountSpent;
}
