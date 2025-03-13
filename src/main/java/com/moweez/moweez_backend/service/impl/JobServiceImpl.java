package com.moweez.moweez_backend.service.impl;


import com.moweez.moweez_backend.dto.JobRequest;
import com.moweez.moweez_backend.dto.JobResponse;
import com.moweez.moweez_backend.entity.Job;
import com.moweez.moweez_backend.exception.ResourceNotFoundException;
import com.moweez.moweez_backend.mapper.JobMapper;
import com.moweez.moweez_backend.repository.JobRepository;
import com.moweez.moweez_backend.service.JobService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public List<JobResponse> getAllJobs() {
        logger.info("Retrieving all jobs.");
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Long id) {
        logger.info("Retrieving job with id: {}", id);
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
        return jobMapper.toResponse(job);
    }

    @Override
    public JobResponse createJob(@Valid JobRequest jobRequest) {
        logger.info("Creating a new job.");
        Job job = jobMapper.toEntity(jobRequest);
        Job savedJob = jobRepository.save(job);
        return jobMapper.toResponse(savedJob);
    }

    @Override
    public JobResponse updateJob(Long id, @Valid JobRequest jobRequest) {
        logger.info("Updating job with id: {}", id);
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

        // Update entity fields from the request (this can also be handled by a custom mapper method)
        existingJob.setJobDateTime(jobRequest.getJobDateTime());
        existingJob.setStatus(jobRequest.getStatus());
        existingJob.setAddress(jobRequest.getAddress());
        existingJob.setLawnSizeSqFt(jobRequest.getLawnSizeSqFt());
        existingJob.setPrice(jobRequest.getPrice());
        existingJob.setNotes(jobRequest.getNotes());
        existingJob.setLatitude(jobRequest.getLatitude());
        existingJob.setLongitude(jobRequest.getLongitude());
        existingJob.setCompletionTimeMinutes(jobRequest.getCompletionTimeMinutes());
        existingJob.setCustomerRating(jobRequest.getCustomerRating());
        existingJob.setServiceProviderRating(jobRequest.getServiceProviderRating());
        existingJob.setJobType(jobRequest.getJobType());
        existingJob.setMaterialsUsed(jobRequest.getMaterialsUsed());
        existingJob.setEquipmentUsed(jobRequest.getEquipmentUsed());

        Job updatedJob = jobRepository.save(existingJob);
        return jobMapper.toResponse(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {
        logger.info("Deleting job with id: {}", id);
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
    }
}
