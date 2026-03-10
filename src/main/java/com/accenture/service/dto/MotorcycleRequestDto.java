package com.accenture.service.dto;

import com.accenture.model.enums.MotorcycleTypes;
import com.accenture.model.enums.Transmission;
import com.accenture.utils.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotorcycleRequestDto(

        @Valid
        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = Messages.MOTORCYCLE_NB_CYLINDERS_NULL)
        @Min(value = 0, message = Messages.MOTORCYCLE_NB_CYLINDERS_NULL)
        Integer nbCylinders,

        @NotNull(message = Messages.MOTORCYCLE_ENGINE_DISPLACEMENT_NULL)
        @Min(value = 0, message = Messages.MOTORCYCLE_ENGINE_DISPLACEMENT_NULL)
        Float engineDisplacement,

        @NotNull(message = Messages.MOTORCYCLE_WEIGHT_NULL)
        @Min(value = 0, message = Messages.MOTORCYCLE_WEIGHT_NULL)
        Float weight,

        @NotNull(message = Messages.MOTORCYCLE_ENGINE_POWER_NULL)
        @Min(value = 0, message = Messages.MOTORCYCLE_ENGINE_POWER_NULL)
        Float enginePower,

        @NotNull(message = Messages.MOTORCYCLE_SEAT_HEIGHT_NULL)
        @Min(value = 0, message = Messages.MOTORCYCLE_SEAT_HEIGHT_NULL)
        Float seatHeight,

        @NotNull(message = Messages.MOTORCYCLE_TRANSMISSION_NULL)
        Transmission transmission,

        @NotNull(message = Messages.MOTORCYCLE_TYPE_NULL)
        MotorcycleTypes motorcycleTypes
) {
}
