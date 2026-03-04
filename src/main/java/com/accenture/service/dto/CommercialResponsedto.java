package com.accenture.service.dto;

import com.accenture.model.enums.CommercialTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.Transmission;

public record CommercialResponsedto(

        int id,

        VehiculeResponseDto vehiculeResponseDto,

        int nbPlaces,
        FuelType fuelType,
        Transmission transmission,
        boolean airConditioning,
        float maximalLoad,
        float weigh,
        float capacity,
        CommercialTypes commercialTypes
) {
}
