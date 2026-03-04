package com.accenture.service.dto;

import com.accenture.model.enums.MotorcycleTypes;
import com.accenture.model.enums.Transmission;

public record MotorcycleResponseDto(
        int id,

        VehiculeResponseDto vehiculeResponseDto,

        int nbCylinders,
        float engineDisplacement,
        float weight,
        float enginePower,
        float seatHeight,
        Transmission transmission,
        MotorcycleTypes motorcycleTypes
) {
}


