package com.accenture.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Types of fuel")
public enum FuelType {

    DIESEL,
    ESSENCE,
    ELECTRIC,
    HYBRID;
}
