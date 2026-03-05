package com.accenture.service.dto;

import com.accenture.model.enums.CommercialTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.Transmission;
import com.accenture.utils.Messages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommercialRequestDto(

        VehiculeRequestDto vehiculeRequestDto,

        @NotNull(message = Messages.COMMERCIAL_NB_PLACES_NULL)
        @Min(value = 0, message = Messages.COMMERCIAL_NB_PLACES_NULL)
        Integer nbPlaces,

        @NotNull(message = Messages.COMMERCIAL_FUEL_TYPE_NULL)
        FuelType fuelType,

        @NotNull(message = Messages.COMMERCIAL_TRANSMMISSION_NULL)
        Transmission transmission,

        @NotNull(message = Messages.COMMERCIAL_AIR_CONDITIONING_NULL)
        Boolean airConditioning,

        @NotNull(message = Messages.COMMERCIAL_MAXIMAL_LOAD_NULL)
        Float maximalLoad,

        @NotNull(message = Messages.COMMERCIAL_WEIGHT_NULL)
        @Min(value = 0, message = Messages.COMMERCIAL_WEIGHT_NULL)
        Float weight,

        @NotNull(message = Messages.COMMERCIAL_CAPACITY_NULL)
        @Min(value = 0, message = Messages.COMMERCIAL_CAPACITY_NULL)
        Float capacity,

        @NotNull(message = Messages.COMMERCIAL_TYPE_NULL)
        CommercialTypes commercialTypes
) {
}
