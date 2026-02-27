package com.accenture.service.dto;

import com.accenture.model.enums.*;

public record CarResponseDto(
        int id,

        String brand,
        String model,
        String color,
//        float dailyBaseRentalRate,
//        float mileage,
//        boolean active,
//        boolean removedFromTheFleet,

        int nbPlaces,
        FuelType fuelType,
        NbDoors nbDoors,
        Transmission transmission,
        Boolean airConditioning,
        int nbLunggage,
        CarTypes carTypes
//        Licences licence
) {
}
