package com.moweez.moweez_backend.mapper;

import com.moweez.moweez_backend.dto.CustomerDTO;
import com.moweez.moweez_backend.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);
    
    @Mapping(target = "password", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);
}