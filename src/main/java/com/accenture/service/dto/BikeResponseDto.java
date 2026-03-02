package com.accenture.service.dto;

import com.accenture.model.enums.BikeTypes;

public record BikeResponseDto(

        int id,

        String brand,
        String model,
        String color,

        float frameSize,
        float weight,
        boolean electric,
        Float batteryCapacity,
        Float autonomy,
        boolean discBrake,
        BikeTypes bikeTypes
) {
}
