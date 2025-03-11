package com.moweez.moweez_backend.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



/**
 * Data Transfer Object for Customer information.
 * <p>
 * This class is immutable and provides a subset of customer data for transfer purposes.
 * </p>
 */
@Data
@Builder

public class CustomerDTO {

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

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private final String address;
}