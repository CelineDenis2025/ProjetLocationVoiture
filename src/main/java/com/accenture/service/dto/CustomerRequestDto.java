package com.accenture.service.dto;

import com.accenture.model.enums.Licenses;
import com.accenture.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public record CustomerRequestDto(

        ConnectedUserRequestDto connectedUserRequestDto,

        @NotBlank(message = "customer.street.null")
        String street,

        @NotBlank(message = "customer.postalCode.null")
        String postalCode,

        @NotBlank(message = "customer.city.null")
        String city,

        @NotNull(message = "customer.dateOfBirth.null")
        LocalDate dateOfBirth,

        @NotNull(message = "customer.licences.null")
        List<Licenses> licenses
        ) {
}