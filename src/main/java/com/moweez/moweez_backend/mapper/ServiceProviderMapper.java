package com.moweez.moweez_backend.mapper;

import com.moweez.moweez_backend.dto.ServiceProviderRequest;
import com.moweez.moweez_backend.dto.ServiceProviderResponse;
import com.moweez.moweez_backend.entity.ServiceProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * MapStruct mapper for converting between ServiceProvider entities and their corresponding DTOs.
 * <p>
 * This mapper provides methods to:
 * - Convert a ServiceProvider entity to a ServiceProviderResponse DTO (output), excluding sensitive fields such as "password".
 * - Convert a ServiceProviderRequest DTO (input) to a ServiceProvider entity.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface ServiceProviderMapper {

    ServiceProviderMapper INSTANCE = Mappers.getMapper(ServiceProviderMapper.class);

    @Mapping(target = "password", ignore = true)
    ServiceProviderResponse toResponse(ServiceProvider serviceProvider);

    ServiceProvider toEntity(ServiceProviderRequest serviceProviderRequest);
}
// Note: The toEntity method is used for creating or updating entities from DTOs, and it includes all fields except the password.