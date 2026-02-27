package com.accenture.service.dto;

import com.accenture.model.enums.CarTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.NbDoors;
import com.accenture.model.enums.Transmission;

public record MotorcycleResponseDto (
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
) {
}
