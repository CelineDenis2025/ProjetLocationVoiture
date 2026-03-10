package com.accenture.service.dto;

import com.accenture.model.enums.CarTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.NbDoors;
import com.accenture.model.enums.Transmission;
import com.accenture.utils.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRequestDto(

        @Valid
        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = Messages.CAR_NB_PLACES_NULL)
        @Min(value = 0, message = Messages.CAR_NB_PLACES_NULL)
        Integer nbPlaces,

        @NotNull(message = Messages.CAR_FUEL_TYPE_NULL)
        FuelType fuelType,

        @NotNull(message = Messages.CAR_NB_DOORS_NULL)
        NbDoors nbDoors,

        @NotNull(message = Messages.CAR_TRANSMISSION_NULL)
        Transmission transmission,

        @NotNull(message = Messages.CAR_AIR_CONDITIONING_NULL)
        Boolean airConditioning,

        @NotNull(message = Messages.CAR_NB_LUGGAGE_MIN)
        @Min(value = 0, message = Messages.CAR_NB_LUGGAGE_MIN)
        Integer nbLuggage,

        @NotNull(message = Messages.CAR_TYPE_NULL)
        CarTypes carTypes
) {
}