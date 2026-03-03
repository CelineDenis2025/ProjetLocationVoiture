package com.accenture.service.dto;

import com.accenture.model.enums.Licenses;

import java.time.LocalDate;
import java.util.List;

public record CustomerResponseDto (

        int id,

        String firstName,
        String lastName,
        String email,
        String street,
        String postalCode,
        String city,
        LocalDate dateOfBirth,
        LocalDate registrationDate,
        List<Licenses> licenses,
        Boolean inactive
){
}
