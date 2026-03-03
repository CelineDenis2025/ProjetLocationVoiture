package com.accenture.mapper;

import com.accenture.model.Customer;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "address", source = "customerRequestDto")
    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CustomerRequestDto customerRequestDto);

    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "postalCode", source = "address.postalCode")
    @Mapping(target = "city", source = "address.city")
    CustomerResponseDto toCustomerResponseDto(Customer customer);
}

