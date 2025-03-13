package com.moweez.moweez_backend.dto;


import com.moweez.moweez_backend.entity.JobStatus;
import com.moweez.moweez_backend.entity.JobType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class JobResponse {

    private Long id;
    private Long customerId;
    private Long serviceProviderId;
    private LocalDateTime jobDateTime;
    private JobStatus status;
    private String address;
    private double lawnSizeSqFt;
    private BigDecimal price;
    private String notes;
    private Double latitude;
    private Double longitude;
    private Integer completionTimeMinutes;
    private Double customerRating;
    private Double serviceProviderRating;
    private JobType jobType;
    private String materialsUsed;
    private String equipmentUsed;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
