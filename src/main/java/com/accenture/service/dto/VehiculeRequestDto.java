package com.accenture.service.dto;

import jakarta.validation.constraints.NotBlank;

public record VehiculeRequestDto(

        @NotBlank(message = "vehicule.brand.null")
        String brand,

        @NotBlank(message = "vehicule.model.null")
        String model,

        @NotBlank(message = "vehicule.color.null")
        String color
) {
}
