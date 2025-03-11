package com.moweez.moweez_backend.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.*;
import java.util.Set;

/**
 * Data Transfer Object for ServiceProvider information.
 * <p>
 * This DTO encapsulates data related to a service provider, providing a secure and efficient means
 * of transferring service provider data between different layers of the Moweez application.
 * This DTO is immutable and is created with a {@link Builder}.
 * </p>
 */
@Data
@Builder
public class ServiceProviderDTO {

    private final Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private final String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private final String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private final String phone;

    @NotBlank(message = "Vehicle details are required")
    @Size(max = 255, message = "Vehicle details cannot exceed 255 characters")
    private final String vehicleDetails;

    @NotBlank(message = "License number is required")
    @Size(max = 50, message = "License number cannot exceed 50 characters")
    private final String licenseNumber;

    private final Set<String> serviceAreas; // Keep it immutable

    private final Set<String> equipmentDetails; // Keep it immutable

    private final double rating;

    private final boolean available;
    
    private final String verificationStatus; //ENUM verification Status
    
     private final double customerFeedbackScore; //ENUM customerFeedbackScore
     
      private final String certifications; //ENUM certifications
       private final double revenuePerLaborHour;

    //add photo of the service providers
      private final double price;
}