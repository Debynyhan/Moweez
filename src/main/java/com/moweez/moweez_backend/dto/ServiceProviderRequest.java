package com.moweez.moweez_backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * DTO for creating or updating a ServiceProvider.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    @NotBlank(message = "Vehicle details are required")
    @Size(max = 255, message = "Vehicle details cannot exceed 255 characters")
    private String vehicleDetails;

    @NotBlank(message = "License number is required")
    @Size(max = 50, message = "License number cannot exceed 50 characters")
    private String licenseNumber;

    private Set<String> serviceAreas;

    private Set<String> equipmentDetails;

    @PositiveOrZero(message = "Rating must be positive or zero")
    private double rating;

    private boolean available;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}