package com.accenture.service.dto;

import com.accenture.model.enums.Licenses;
import com.accenture.model.enums.Role;
import com.accenture.utils.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public record CustomerRequestDto(

        @Valid
        ConnectedUserRequestDto connectedUserRequestDto,

        @NotBlank(message = Messages.CUSTOMER_STREET_NULL)
        String street,

        @NotBlank(message = Messages.CUSTOMER_POSTAL_CODE_NULL)
        String postalCode,

        @NotBlank(message = Messages.CUSTOMER_CITY_NULL)
        String city,

        @NotNull(message = Messages.CUSTOMER_DATE_OF_BIRTH_NULL)
        LocalDate dateOfBirth,

        @NotNull(message = Messages.CUSTOMER_LICENSES_NULL)
        List<Licenses> licenses
        ) {
}