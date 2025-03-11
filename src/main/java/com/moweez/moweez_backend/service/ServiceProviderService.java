package com.moweez.moweez_backend.service;

import com.moweez.moweez_backend.dto.ServiceProviderRequest;
import com.moweez.moweez_backend.dto.ServiceProviderResponse;
import jakarta.validation.Valid;
import java.util.List;

public interface ServiceProviderService {

    /**
     * Retrieves all service providers.
     * 
     * @return a list of ServiceProviderResponse objects.
     */
    List<ServiceProviderResponse> getAllServiceProviders();

    /**
     * Retrieves a service provider by their ID.
     * 
     * @param id the ID of the service provider.
     * @return the ServiceProviderResponse.
     */
    ServiceProviderResponse getServiceProviderById(Long id);

    /**
     * Creates a new service provider.
     * 
     * @param request the ServiceProviderRequest containing service provider data.
     * @return the created ServiceProviderResponse.
     */
    ServiceProviderResponse createServiceProvider(@Valid ServiceProviderRequest request);

    /**
     * Updates an existing service provider by ID.
     * 
     * @param id the ID of the service provider to update.
     * @param request the ServiceProviderRequest containing updated data.
     * @return the updated ServiceProviderResponse.
     */
    ServiceProviderResponse updateServiceProvider(Long id, @Valid ServiceProviderRequest request);

    /**
     * Deletes a service provider by their ID.
     * 
     * @param id the ID of the service provider to delete.
     */
    void deleteServiceProvider(Long id);

    /**
     * Verifies a service provider (e.g., after background checks).
     * 
     * @param id the ID of the service provider.
     * @return the updated ServiceProviderResponse with verification status.
     */
    ServiceProviderResponse verifyServiceProvider(Long id);

    /**
     * Updates the availability status of a service provider.
     * 
     * @param id the ID of the service provider.
     * @param available whether the provider is available.
     * @return the updated ServiceProviderResponse.
     */
    ServiceProviderResponse updateAvailability(Long id, boolean available);
}
