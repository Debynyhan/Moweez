package com.moweez.moweez_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "drivers")
@Data
public class Driver implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(unique = true)
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

    private double rating = 0.0;

    private boolean available = false;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;  // Store the HASHED password, never plain text

    private String verificationStatus = "PENDING"; // PENDING, VERIFIED, REJECTED
    private boolean enabled = true; // Account enabled/disabled

    @Enumerated(EnumType.STRING)
    private Role role = Role.DRIVER;

    // Spring Security UserDetails Implementation

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Using email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic if accounts expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic if accounts can be locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic if credentials expire
    }

    @Override
    public boolean isEnabled() {
        return enabled; // Use the 'enabled' field
    }
}