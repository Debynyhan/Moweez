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

@Data
@Builder
public class JobRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    // Service provider might not be assigned at creation, so this field can be optional.
    private Long serviceProviderId;

    @NotNull(message = "Job date and time are required")
    private LocalDateTime jobDateTime;

    @NotNull(message = "Job status is required")
    private JobStatus status;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @Positive(message = "Lawn size must be positive")
    private double lawnSizeSqFt;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;

    @Digits(integer = 3, fraction = 6, message = "Latitude must be a valid coordinate")
    private Double latitude;

    @Digits(integer = 3, fraction = 6, message = "Longitude must be a valid coordinate")
    private Double longitude;

    @PositiveOrZero(message = "Completion time must be zero or positive")
    private Integer completionTimeMinutes;

    @Min(value = 1, message = "Customer rating must be between 1 and 5")
    @Max(value = 5, message = "Customer rating must be between 1 and 5")
    private Double customerRating;

    @Min(value = 1, message = "Service provider rating must be between 1 and 5")
    @Max(value = 5, message = "Service provider rating must be between 1 and 5")
    private Double serviceProviderRating;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @Size(max = 255, message = "Materials used cannot exceed 255 characters")
    private String materialsUsed;

    @Size(max = 255, message = "Equipment used cannot exceed 255 characters")
    private String equipmentUsed;

        // Optional analytics fields
    @CreatedDate // Marks field to be automatically populated upon creation
    @Column(name = "created_date", updatable = false)
    private final LocalDateTime createdDate;

    @LastModifiedDate // Marks field to be automatically populated upon last modification
    @Column(name = "last_modified_date")
    private final LocalDateTime lastModifiedDate;    
}
// Note: This class is used for creating or updating a job and includes validation annotations to ensure data integrity.
 // Sensitive fields like customer ID and service provider ID are included for reference.
 // The status field is required to track the job's progress.
 // The job type field is required to specify the nature of the job.
 // The notes field is optional for additional information.
 // The materials and equipment fields are optional for tracking resources used.
 // The completion time, customer rating, and service provider rating fields are optional for feedback purposes.