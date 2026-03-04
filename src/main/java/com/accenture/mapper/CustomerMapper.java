package com.accenture.mapper;

import com.accenture.model.Admin;
import com.accenture.model.Customer;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "address", source = "customerRequestDto")
    @Mapping(target = "firstName", source = "connectedUserRequestDto.firstName")
    @Mapping(target = "lastName", source = "connectedUserRequestDto.lastName")
    @Mapping(target = "email", source = "connectedUserRequestDto.email")
    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CustomerRequestDto customerRequestDto);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    ConnectedUserResponseDto toConnectedUserResponseDto(Customer customer);

    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "postalCode", source = "address.postalCode")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "connectedUserResponseDto", source = ".")
    CustomerResponseDto toCustomerResponseDto(Customer customer);
}


