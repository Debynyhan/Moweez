package com.moweez.moweez_backend.service;


import com.moweez.moweez_backend.dto.JobRequest;
import com.moweez.moweez_backend.dto.JobResponse;
import jakarta.validation.Valid;
import java.util.List;

public interface JobService {

    /**
     * Retrieves a list of all job requests.
     * 
     * @return a list of JobResponse DTOs.
     */
    List<JobResponse> getAllJobs();

    /**
     * Retrieves a job by its ID.
     * 
     * @param id the job ID.
     * @return the corresponding JobResponse DTO.
     */
    JobResponse getJobById(Long id);

    /**
     * Creates a new job request.
     * 
     * @param jobRequest the JobRequest DTO containing job details.
     * @return the created JobResponse DTO.
     */
    JobResponse createJob(@Valid JobRequest jobRequest);

    /**
     * Updates an existing job.
     * 
     * @param id the job ID.
     * @param jobRequest the JobRequest DTO containing updated job details.
     * @return the updated JobResponse DTO.
     */
    JobResponse updateJob(Long id, @Valid JobRequest jobRequest);

    /**
     * Deletes a job by its ID.
     * 
     * @param id the job ID.
     */
    void deleteJob(Long id);
}
