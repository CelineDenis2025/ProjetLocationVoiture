package com.accenture.service.dto;

import com.accenture.model.enums.CommercialTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommercialRequestDto(

        @NotBlank(message = "vehicule.brand.null")
        String brand,

        @NotBlank(message = "vehicule.model.null")
        String model,

        @NotBlank(message = "vehicule.color.null")
        String color,

        @NotNull(message = "commercial.nbPlaces.null")
        @Min(value = 0, message = "commercial.nbPlaces.null")
        Integer nbPlaces,

        @NotNull(message = "commercial.fuelType.null")
        FuelType fuelType,

        @NotNull(message = "commercial.transmission.null")
        Transmission transmission,

        @NotNull(message = "commercial.airConditioning.null")
        Boolean airConditioning,

        @NotNull(message = "commercial.maximalLoad.null")
        Float maximalLoad,

        @NotNull(message = "commercial.weight.null")
        @Min(value = 0, message = "commercial.weight.null")
        Float weight,

        @NotNull(message = "commercial.capacity.null")
        @Min(value = 0, message = "commercial.capacity.null")
        Float capacity,

        @NotNull(message = "commercial.commercialTypes.null")
        CommercialTypes commercialTypes
) {
}
