package com.accenture.service.dto;

import com.accenture.model.enums.*;

public record CarResponseDto(
        int id,

        VehiculeResponseDto vehiculeResponseDto,

        int nbPlaces,
        FuelType fuelType,
        NbDoors nbDoors,
        Transmission transmission,
        Boolean airConditioning,
        int nbLuggage,
        CarTypes carTypes

) {
}
