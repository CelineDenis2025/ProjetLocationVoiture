package com.accenture.service.dto;

import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.RecreationalTypes;
import com.accenture.model.enums.Transmission;

public record RecreationalResponseDto(

        int id,

        String brand,
        String model,
        String color,

        int nbPlace,
        FuelType fuelType,
        Transmission transmission,
        boolean airConditioning,
        float weight,
        float height,
        int nbBerths,
        boolean providedKitchenEquipment,
        boolean providedBedding,
        boolean refregiratorEquipment,
        boolean showerEquipment,
        RecreationalTypes recreationalTypes
        ) {
}
