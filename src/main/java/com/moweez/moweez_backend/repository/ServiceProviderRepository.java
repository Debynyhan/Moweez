package com.moweez.moweez_backend.repository;

import com.moweez.moweez_backend.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository interface for {@link ServiceProvider} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and query methods for {@link ServiceProvider} entities.
 * </p>
 */
@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    /**
     * Retrieves a service provider by their email address.
     *
     * @param email the email address of the service provider
     * @return an {@link Optional} containing the found service provider, or {@link Optional#empty()} if no service provider is found
     */
    Optional<ServiceProvider> findByEmail(String email);

    /**
     * Checks if a service provider exists by their email address.
     *
     * @param email the email address to check
     * @return {@code true} if a service provider exists with the given email address, {@code false} otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a list of service providers by their availability status.
     *
     * @param available the availability status of the service providers
     * @return a list of service providers with the specified availability status
     */
    List<ServiceProvider> findByAvailable(boolean available);

    /**
     * Retrieves a list of service providers by their verification status.
     *
     * @param verificationStatus the verification status of the service providers
     * @return a list of service providers with the specified verification status
     */
    List<ServiceProvider> findByVerificationStatus(String verificationStatus);

    // Additional query methods can be defined here as needed
}
