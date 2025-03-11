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
@Table(name = "jobs", indexes = {
        @Index(name = "idx_jobs_customer_id", columnList = "customer_id"),
        @Index(name = "idx_jobs_service_provider_id", columnList = "service_provider_id"),
        @Index(name = "idx_jobs_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_provider_id", nullable = false)
    private ServiceProvider serviceProvider;

    @NotNull(message = "Job date and time are required")
    @Column(name = "job_date_time", nullable = false)
    private LocalDateTime jobDateTime;

    @NotNull(message = "Job status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private JobStatus status;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Positive(message = "Lawn size must be positive")
    @Column(name = "lawn_size_sq_ft", nullable = false)
    private double lawnSizeSqFt;

    @Positive(message = "Price must be positive")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    @Column(name = "notes", length = 500)
    private String notes;

    @Digits(integer = 3, fraction = 6, message = "Latitude must be a valid coordinate")
    @Column(name = "latitude", precision = 10, scale = 6)
    private Double latitude;

    @Digits(integer = 3, fraction = 6, message = "Longitude must be a valid coordinate")
    @Column(name = "longitude", precision = 10, scale = 6)
    private Double longitude;

    @PositiveOrZero(message = "Completion time must be zero or positive")
    @Column(name = "completion_time_minutes")
    private Integer completionTimeMinutes;

    @Min(value = 1, message = "Customer rating must be between 1 and 5")
    @Max(value = 5, message = "Customer rating must be between 1 and 5")
    @Column(name = "customer_rating")
    private Double customerRating;

    @Min(value = 1, message = "Service provider rating must be between 1 and 5")
    @Max(value = 5, message = "Service provider rating must be between 1 and 5")
    @Column(name = "service_provider_rating")
    private Double serviceProviderRating;

    @NotNull(message = "Job type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false, length = 50)
    private JobType jobType;

    @Size(max = 255, message = "Materials used cannot exceed 255 characters")
    @Column(name = "materials_used", length = 255)
    private String materialsUsed;

    @Size(max = 255, message = "Equipment used cannot exceed 255 characters")
    @Column(name = "equipment_used", length = 255)
    private String equipmentUsed;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}

