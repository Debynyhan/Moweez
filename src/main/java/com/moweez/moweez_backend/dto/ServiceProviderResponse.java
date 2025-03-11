package com.moweez.moweez_backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for representing a ServiceProvider in responses.
 * Sensitive fields such as password are excluded.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String vehicleDetails;
    private String licenseNumber;
    private Set<String> serviceAreas;
    private Set<String> equipmentDetails;
    private double rating;
    private boolean available;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
// Note: This class is used for responses and does not include sensitive fields like password.