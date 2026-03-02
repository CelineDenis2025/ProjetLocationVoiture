package com.accenture.service.dto;

import com.accenture.model.enums.BikeTypes;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BikeRequestDto(

        @NotBlank(message = "vehicule.brand.null")
        String brand,

        @NotBlank(message = "vehicule.model.null")
        String model,

        @NotBlank(message = "vehicule.color.null")
        String color,

        @NotNull(message = "bike.frameSize.null")
        @Min(value = 0, message = "bike.frameSize.null")
        Float frameSize,

        @NotNull(message = "bike.weight.null")
        @Min(value = 0, message = "bike.weight.null")
        Float weight,

        @NotNull(message = "bike.electric.null")
        Boolean electric,

        Float batteryCapacity,
        Float autonomy,

        @NotNull(message = "bike.discBrake.null")
        Boolean discBrake,

        @NotNull(message = "bike.bikeType.null")
        BikeTypes bikeTypes
) {
}
