package com.moweez.moweez_backend.mapper;

import com.moweez.moweez_backend.dto.CustomerRequest;
import com.moweez.moweez_backend.dto.CustomerResponse;
import com.moweez.moweez_backend.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    /**
     * Maps a CustomerRequest DTO to a Customer entity.
     * This mapping includes the password so it can be processed during creation or update.
     */
    Customer toEntity(CustomerRequest customerRequest);

    /**
     * Maps a Customer entity to a CustomerResponse DTO.
     * The password field is ignored to prevent exposing sensitive data.
     */
    @Mapping(target = "password", ignore = true)
    CustomerResponse toResponse(Customer customer);


    
}