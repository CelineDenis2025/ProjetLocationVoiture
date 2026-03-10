package com.accenture.service.dto;

import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.RecreationalTypes;
import com.accenture.model.enums.Transmission;
import com.accenture.utils.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecreationalRequestDto(

        @Valid
        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = Messages.RECREATIONAL_NB_PLACES_NULL)
        @Min(value = 0, message = Messages.RECREATIONAL_NB_PLACES_NULL)
        Integer nbPlaces,

        @NotNull(message = Messages.RECREATIONAL_FUEL_TYPE_NULL)
        FuelType fuelType,

        @NotNull(message = Messages.RECREATIONAL_TRANSMISSION_NULL)
        Transmission transmission,

        @NotNull(message = Messages.RECREATIONAL_AIR_CONDITIONING_NULL)
        Boolean airConditioning,

        @NotNull(message = Messages.RECREATIONAL_WEIGHT_NULL)
        @Min(value = 0, message = Messages.RECREATIONAL_WEIGHT_NULL)
        Float weight,

        @NotNull(message = Messages.RECREATIONAL_HEIGHT_NULL)
        @Min(value = 0, message = Messages.RECREATIONAL_HEIGHT_NULL)
        Float height,

        @NotNull(message = Messages.RECREATIONAL_NB_BERTHS_NULL)
        @Min(value = 0, message = Messages.RECREATIONAL_NB_BERTHS_NULL)
        Integer nbBerths,

        @NotNull(message = Messages.RECREATIONAL_PROVIDED_KITCHEN_EQUIPMENT_NULL)
        Boolean providedKitchenEquipment,

        @NotNull(message = Messages.RECREATIONAL_PROVIDED_BEDDING_NULL)
        Boolean providedBedding,

        @NotNull(message = Messages.RECREATIONAL_REFREGIRATOR_EQUIPMENT_NULL)
        Boolean refregiratorEquipment,

        @NotNull(message = Messages.RECREATIONAL_SHOWER_EQUIPMENT_NULL)
        Boolean showerEquipment,

        @NotNull(message = Messages.RECREATIONAL_TYPE_NULL)
        RecreationalTypes recreationalTypes
) {
}
