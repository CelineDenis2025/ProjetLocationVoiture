package com.accenture.service.dto;

import com.accenture.utils.Messages;
import jakarta.validation.constraints.NotBlank;

public record VehiculeRequestDto(

        @NotBlank(message = Messages.VEHICULE_BRAND_NULL)
        String brand,

        @NotBlank(message = Messages.VEHICULE_BRAND_NULL)
        String model,

        @NotBlank(message = Messages.VEHICULE_BRAND_NULL)
        String color
) {
}
