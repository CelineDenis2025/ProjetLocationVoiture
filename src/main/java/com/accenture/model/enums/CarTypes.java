package com.accenture.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Types of car")
public enum CarTypes {

    CITY_CAR,
    SEDAN,
    SUV,
    FAMILY_CAR,
    ELECTRIC_CAR,
    LUXURY_CAR;
}
