package com.accenture.service.dto;

import com.accenture.model.enums.MotorcycleTypes;
import com.accenture.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotorcycleRequestDto(

        @NotBlank(message = "vehicule.brand.null")
        String brand,

        @NotBlank(message = "vehicule.model.null")
        String model,

        @NotBlank(message = "vehicule.color.null")
        String color,

        @NotNull(message = "motorcyle.nbCylinders.null")
        @Min(value = 0, message = "motorcyle.nbCylinders.null")
        Integer nbCylinders,

        @NotNull(message = "motorcyle.engineDisplacement.null")
        @Min(value = 0, message = "motorcyle.engineDisplacement.null")
        Float engineDisplacement,

        @NotNull(message = "motorcyle.weight.null")
        @Min(value = 0, message = "motorcyle.weight.null")
        Float weight,

        @NotNull(message = "motorcyle.enginePower.null")
        @Min(value = 0, message = "motorcyle.enginePower.null")
        Float enginePower,

        @NotNull(message = "motorcyle.seatHeight.null")
        @Min(value = 0, message = "motorcyle.seatHeight.null")
        Float seatHeight,

        @NotNull(message = "motorcyle.transmission.null")
        Transmission transmission,

        @NotNull(message = "motorcyle.motorcycleTypes.null")
        MotorcycleTypes motorcycleTypes
) {
}
