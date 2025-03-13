package com.moweez.moweez_backend.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.moweez.moweez_backend.dto.JobRequest;
import com.moweez.moweez_backend.dto.JobResponse;
import com.moweez.moweez_backend.entity.Job;


public interface JobMapper {


    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(target = "password", ignore = true)
    JobResponse toResponse(Job Job);

    Job toEntity(JobRequest JobRequest);

}
