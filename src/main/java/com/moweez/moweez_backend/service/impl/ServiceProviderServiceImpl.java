package com.moweez.moweez_backend.service.impl;

import com.moweez.moweez_backend.dto.ServiceProviderRequest;
import com.moweez.moweez_backend.dto.ServiceProviderResponse;
import com.moweez.moweez_backend.entity.ServiceProvider;
import com.moweez.moweez_backend.exception.ResourceNotFoundException;
import com.moweez.moweez_backend.mapper.ServiceProviderMapper;
import com.moweez.moweez_backend.repository.ServiceProviderRepository;
import com.moweez.moweez_backend.service.ServiceProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceProviderServiceImpl implements ServiceProviderService {

   
    private final ServiceProviderRepository serviceProviderRepository;
    private final ServiceProviderMapper serviceProviderMapper;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderServiceImpl.class);

    public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository,
                                      ServiceProviderMapper serviceProviderMapper,
                                      PasswordEncoder passwordEncoder) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.serviceProviderMapper = serviceProviderMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ServiceProviderResponse> getAllServiceProviders() {
        logger.info("Retrieving all service providers.");
        return serviceProviderRepository.findAll()
                .stream()
                .map(serviceProviderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceProviderResponse getServiceProviderById(Long id) {
        logger.info("Retrieving service provider with ID: {}", id);
        ServiceProvider provider = findServiceProviderById(id);
        return serviceProviderMapper.toResponse(provider);
    }

    @Override
    public ServiceProviderResponse createServiceProvider(@Valid ServiceProviderRequest request) {
        logger.info("Creating a new service provider with email: {}", request.getEmail());
        // Check for duplicate email
        if (serviceProviderRepository.existsByEmail(request.getEmail())) {
            throw new ResourceNotFoundException("Email is already taken: " + request.getEmail());
        }
        // Map input DTO to entity
        ServiceProvider provider = serviceProviderMapper.toEntity(request);
        // Encode the password securely
        provider.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            ServiceProvider savedProvider = serviceProviderRepository.save(provider);
            return serviceProviderMapper.toResponse(savedProvider);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Could not create service provider due to data integrity violation: " + e.getMessage(), e);
        }
    }

    @Override
    public ServiceProviderResponse updateServiceProvider(Long id, @Valid ServiceProviderRequest request) {
        logger.info("Updating service provider with ID: {}", id);
        ServiceProvider existingProvider = findServiceProviderById(id);

        // Update mutable fields
        existingProvider.setFirstName(request.getFirstName());
        existingProvider.setLastName(request.getLastName());
        // If email is changed, verify uniqueness
        if (!existingProvider.getEmail().equals(request.getEmail())) {
            if (serviceProviderRepository.existsByEmail(request.getEmail())) {
                throw new ResourceNotFoundException("Email is already taken: " + request.getEmail());
            }
            existingProvider.setEmail(request.getEmail());
        }
        existingProvider.setPhone(request.getPhone());
        existingProvider.setVehicleDetails(request.getVehicleDetails());
        existingProvider.setLicenseNumber(request.getLicenseNumber());
        existingProvider.setAvailable(request.isAvailable());
        // Update password only if provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existingProvider.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        try {
            ServiceProvider updatedProvider = serviceProviderRepository.save(existingProvider);
            return serviceProviderMapper.toResponse(updatedProvider);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Could not update service provider due to data integrity violation: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteServiceProvider(Long id) {
        logger.info("Deleting service provider with ID: {}", id);
        // Ensure the provider exists before deletion
        findServiceProviderById(id);
        serviceProviderRepository.deleteById(id);
    }

    @Override
    public ServiceProviderResponse verifyServiceProvider(Long id) {
        logger.info("Verifying service provider with ID: {}", id);
        ServiceProvider provider = findServiceProviderById(id);
        provider.setVerificationStatus("VERIFIED");
        ServiceProvider updatedProvider = serviceProviderRepository.save(provider);
        return serviceProviderMapper.toResponse(updatedProvider);
    }

    @Override
    public ServiceProviderResponse updateAvailability(Long id, boolean available) {
        logger.info("Updating availability for service provider with ID: {}", id);
        ServiceProvider provider = findServiceProviderById(id);
        provider.setAvailable(available);
        ServiceProvider updatedProvider = serviceProviderRepository.save(provider);
        return serviceProviderMapper.toResponse(updatedProvider);
    }

    // Private helper method to retrieve a service provider by ID
    private ServiceProvider findServiceProviderById(Long id) {
        return serviceProviderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service provider not found with id: " + id));
    }
}
