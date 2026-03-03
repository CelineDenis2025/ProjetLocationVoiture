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

        @NotBlank(message = "connectedUser.firstName.null")
        String firstName,

        @NotBlank(message = "connectedUser.lastName.null")
        String lastName,

        @NotBlank(message = "connectedUser.email.null")
        @Email(message = "customer.email.invalid")
        String email,

        @NotBlank(message = "connectedUser.password.null")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$",
                message = "customer.password.invalid")
        String password,

        @NotBlank(message = "customer.street.null")
        String street,

        @NotBlank(message = "customer.postalCode.null")
        String postalCode,

        @NotBlank(message = "customer.city.null")
        String city,

        @NotNull(message = "customer.dateOfBirth.null")
        LocalDate dateOfBirth,

        @NotNull(message = "customer.licences.null")
        List<Licenses> licenses,

        @NotNull(message = "customer.role.null")
        Role role
        ) {
}