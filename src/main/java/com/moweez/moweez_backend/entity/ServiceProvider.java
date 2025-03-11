package com.moweez.moweez_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.CredentialsContainer;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drivers", indexes = { @Index(name = "idx_drivers_email", columnList = "email") })
@EntityListeners(AuditingEntityListener.class) // Enable JPA Auditing for this entity
public class ServiceProvider implements CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Column(nullable = false, length = 20)
    private String phone;

    @NotBlank(message = "Vehicle details are required")
    @Size(max = 255, message = "Vehicle details cannot exceed 255 characters")
    @Column(name = "vehicle_details", nullable = false, length = 255)
    private String vehicleDetails;

    @NotBlank(message = "License number is required")
    @Size(max = 50, message = "License number cannot exceed 50 characters")
    @Column(name = "license_number", nullable = false, length = 50)
    private String licenseNumber;

    @ElementCollection
    @CollectionTable(name = "driver_service_areas", joinColumns = @JoinColumn(name = "driver_id"))
    @Column(name = "service_area")
    private Set<String> serviceAreas;

    @ElementCollection
    @CollectionTable(name = "driver_equipment", joinColumns = @JoinColumn(name = "driver_id"))
    @Column(name = "equipment")
    private Set<String> equipmentDetails;
    
    @ElementCollection
    @CollectionTable(name = "driver_availability", joinColumns = @JoinColumn(name = "driver_id"))
    @Column(name = "available_time")
    private Set<LocalDateTime> availabilitySchedule;
    
    @Column(name = "job_completion_rate", nullable = false)
    private double jobCompletionRate = 0.0;
    
    @Column(name = "safety_incident_count", nullable = false)
    private int safetyIncidentCount = 0;

    @Column(name = "customer_feedback_score", nullable = false)
    private double customerFeedbackScore = 0.0;
    
    @ElementCollection
    @CollectionTable(name = "service_provider_certifications", joinColumns = @JoinColumn(name = "service_provider_id"))
    @Column(name = "certification")
    private Set<String> certifications;
    
    @Column(name = "revenue_per_labor_hour", nullable = false)
    private double revenuePerLaborHour = 0.0;

    @Column(nullable = false)
    private double rating = 0.0;

    @Column(nullable = false)
    private boolean available = false;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;  // Store the hashed password, never plain text

    @Column(name = "verification_status", nullable = false, length = 20)
    private String verificationStatus = "PENDING"; // PENDING, VERIFIED, REJECTED

    @Column(nullable = false)
    private boolean enabled = true; // Account enabled/disabled

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.SERVICE_PROVIDER; //

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
