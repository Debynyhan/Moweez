package com.moweez.moweez_backend.repository;

import com.moweez.moweez_backend.entity.Job;
import com.moweez.moweez_backend.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for {@link Job} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and query methods for {@link Job} entities.
 * </p>
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Retrieves a list of jobs assigned to a specific service provider.
     *
     * @param serviceProviderId the ID of the service provider
     * @return a list of jobs assigned to the specified service provider
     */
    List<Job> findByServiceProviderId(Long serviceProviderId);

    /**
     * Retrieves a list of jobs associated with a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of jobs associated with the specified customer
     */
    List<Job> findByCustomerId(Long customerId);

    /**
     * Retrieves a list of jobs with a specific status.
     *
     * @param status the status of the jobs to retrieve
     * @return a list of jobs with the specified status
     */
    List<Job> findByStatus(JobStatus status);

    /**
     * Retrieves a list of jobs scheduled between the specified start and end dates.
     *
     * @param startDateTime the start date and time
     * @param endDateTime   the end date and time
     * @return a list of jobs scheduled between the specified dates
     */
    List<Job> findByJobDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Additional query methods can be defined here as needed
}
