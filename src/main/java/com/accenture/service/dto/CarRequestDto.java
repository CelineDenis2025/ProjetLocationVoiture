package com.accenture.service.dto;

import com.accenture.model.enums.CarTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.NbDoors;
import com.accenture.model.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarRequestDto(

        @NotBlank(message = "vehicule.brand.null")
        String brand,

        @NotBlank(message = "vehicule.model.null")
        String model,

        @NotBlank(message = "vehicule.color.null")
        String color,
//        @NotNull(message = "vehicule.dailyBaseRentalRate.null")
//        Float dailyBaseRentalRate,
//        @NotNull(message = "vehicule.mileage.null")
//        Float mileage,
//        @NotNull(message = "vehicule.active.null")
//        Boolean active,
//        @NotNull(message = "vehicule.removedFromTheFleet.null")
//        Boolean removedFromTheFleet,


        @NotNull(message = "car.nbPlaces.null")
        @Min(value = 0, message = "car.nbPlaces.null")
        Integer nbPlaces,

        @NotNull(message = "car.fuelType.null")
        FuelType fuelType,

        @NotNull(message = "car.nbDoors.null")
        NbDoors nbDoors,

        @NotNull(message = "car.transmission.null")
        Transmission transmission,

        @NotNull(message = "car.airConditioning.null")
        Boolean airConditioning,

        @NotNull(message = "car.nbLunggage.min")
        @Min(value = 0, message = "car.nbLunggage.min")
        Integer nbLunggage,

        @NotNull(message = "car.carType.null")
        CarTypes carTypes

//        @NotNull(message = "car.licence.null")
//        Licences licence
) {
}