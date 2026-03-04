package com.accenture.service.dto;

import com.accenture.model.enums.CarTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.NbDoors;
import com.accenture.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRequestDto(

        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = "car.nbPlaces.null")
        @Min(value = 0, message = "car.nbPlaces.null")
        Integer nbPlaces,

        @NotNull(message = "car.fuelType.null")
        FuelType fuelType,

        @NotNull(message = "car.nbDoors.null")
        NbDoors nbDoors,

        @NotNull(message = "car.transmission.null")
        Transmission transmission,

        @NotNull(message = "car.airConditioning.null")
        Boolean airConditioning,

        @NotNull(message = "car.nbLunggage.min")
        @Min(value = 0, message = "car.nbLunggage.min")
        Integer nbLunggage,

        @NotNull(message = "car.carType.null")
        CarTypes carTypes
) {
}