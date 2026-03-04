package com.accenture.service.dto;

import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.RecreationalTypes;
import com.accenture.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecreationalRequestDto(

        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = "recreational.nbPlaces.null")
        @Min(value = 0, message = "recreational.nbPlaces.null")
        Integer nbPlaces,

        @NotNull(message = "recreational.fuelType.null")
        FuelType fuelType,

        @NotNull(message = "recreational.transmission.null")
        Transmission transmission,

        @NotNull(message = "recreational.airConditioning.null")
        Boolean airConditioning,

        @NotNull(message = "recreational.weight.null")
        @Min(value = 0, message = "recreational.weight.null")
        Float weight,

        @NotNull(message = "recreational.height.null")
        @Min(value = 0, message = "recreational.height.null")
        Float height,

        @NotNull(message = "recreational.nbBerths.null")
        @Min(value = 0, message = "recreational.nbBerths.null")
        Integer nbBerths,

        @NotNull(message = "recreational.provided.kitchen.equipment.null")
        Boolean providedKitchenEquipment,

        @NotNull(message = "recreational.provided.bedding.null")
        Boolean providedBedding,

        @NotNull(message = "recreational.refregirator.equipment.null")
        Boolean refregiratorEquipment,

        @NotNull(message = "recreational.shower.equipment.null")
        Boolean showerEquipment,

        @NotNull(message = "recreational.recreationalTypes.null")
        RecreationalTypes recreationalTypes
) {
}
