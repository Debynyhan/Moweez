package com.moweez.moweez_backend.repository;

import com.moweez.moweez_backend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Subscription} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and query methods for {@link Subscription} entities.
 * </p>
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Retrieves a list of subscriptions associated with a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of subscriptions associated with the specified customer
     */
    List<Subscription> findByCustomerId(Long customerId);

    /**
     * Retrieves a subscription by its type and customer ID.
     *
     * @param type       the type of the subscription
     * @param customerId the ID of the customer
     * @return an {@link Optional} containing the found subscription, or {@link Optional#empty()} if no subscription is found
     */
    Optional<Subscription> findByTypeAndCustomerId(String type, Long customerId);

    /**
     * Retrieves a list of active subscriptions.
     *
     * @param active the active status of the subscriptions
     * @return a list of active subscriptions
     */
    List<Subscription> findByActive(boolean active);

    // Additional query methods can be defined here as needed
}
