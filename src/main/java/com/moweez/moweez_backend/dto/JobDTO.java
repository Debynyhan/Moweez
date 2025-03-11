package com.moweez.moweez_backend.dto;

import com.moweez.moweez_backend.entity.JobStatus;
import com.moweez.moweez_backend.entity.JobType;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.Column;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Data Transfer Object for Job information.
 * <p>
 * This DTO encapsulates data related to a job, providing a secure and efficient means
 * of transferring job data between different layers of the Moweez application.
 * This DTO is immutable and is created with a {@link Builder}.
 * </p>
 */
@Data
@Builder
public class JobDTO {
    
    private final Long id;

    @NotNull(message = "Customer ID is required")
    private final Long customerId;

    @NotNull(message = "Service Provider ID is required")
    private final Long serviceProviderId;

    @NotNull(message = "Job date and time are required")
    private final LocalDateTime jobDateTime;

    @NotNull(message = "Job status is required")
    private final JobStatus status;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private final String address;

    @Positive(message = "Lawn size must be positive")
    private final double lawnSizeSqFt;

    @Positive(message = "Price must be positive")
    private final BigDecimal price;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private final String notes;

    @Digits(integer = 3, fraction = 6, message = "Latitude must be a valid coordinate")
    private final Double latitude;

    @Digits(integer = 3, fraction = 6, message = "Longitude must be a valid coordinate")
    private final Double longitude;

    @PositiveOrZero(message = "Completion time must be zero or positive")
    private final Integer completionTimeMinutes;

    @Min(value = 1, message = "Customer rating must be between 1 and 5")
    @Max(value = 5, message = "Customer rating must be between 1 and 5")
    private final Double customerRating;

    @Min(value = 1, message = "Service provider rating must be between 1 and 5")
    @Max(value = 5, message = "Service provider rating must be between 1 and 5")
    private final Double serviceProviderRating;

    @NotNull(message = "Job type is required")
    private final JobType jobType;

    @Size(max = 255, message = "Materials used cannot exceed 255 characters")
    private final String materialsUsed;

    @Size(max = 255, message = "Equipment used cannot exceed 255 characters")
    private final String equipmentUsed;

    // Optional analytics fields
    @CreatedDate // Marks field to be automatically populated upon creation
    @Column(name = "created_date", updatable = false)
    private final LocalDateTime createdDate;

    @LastModifiedDate // Marks field to be automatically populated upon last modification
    @Column(name = "last_modified_date")
    private final LocalDateTime lastModifiedDate;    
    
}