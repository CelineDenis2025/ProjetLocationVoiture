package com.accenture.service.dto;

import com.accenture.model.enums.BikeTypes;
import com.accenture.utils.Messages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BikeRequestDto(

        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = Messages.BIKE_FRAMESIZE_NULL)
        @Min(value = 0, message = Messages.BIKE_FRAMESIZE_NULL)
        Float frameSize,

        @NotNull(message = Messages.BIKE_WEIGHT_NULL)
        @Min(value = 0, message = Messages.BIKE_WEIGHT_NULL)
        Float weight,

        @NotNull(message = Messages.BIKE_ELECTRIC_NULL)
        Boolean electric,

        Float batteryCapacity,
        Float autonomy,

        @NotNull(message = Messages.BIKE_DISCBRAKE_NULL)
        Boolean discBrake,

        @NotNull(message = Messages.BIKE_TYPE_NULL)
        BikeTypes bikeTypes
) {
}
