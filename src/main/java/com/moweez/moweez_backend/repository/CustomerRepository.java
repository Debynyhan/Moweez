package com.moweez.moweez_backend.repository;

import com.moweez.moweez_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link Customer} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and query methods for {@link Customer} entities.
 * </p>
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Retrieves a customer by their email address.
     *
     * @param email the email address of the customer
     * @return an {@link Optional} containing the found customer, or {@link Optional#empty()} if no customer is found
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Checks if a customer exists by their email address.
     *
     * @param email the email address to check
     * @return {@code true} if a customer exists with the given email address, {@code false} otherwise
     */
    boolean existsByEmail(String email);

    // Additional query methods can be defined here as needed
}
