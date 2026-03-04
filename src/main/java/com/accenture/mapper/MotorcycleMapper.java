package com.accenture.mapper;

import com.accenture.model.Car;
import com.accenture.model.Motorcycle;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MotorcycleMapper {

    @Mapping(target = "brand", source = "vehiculeRequestDto.brand")
    @Mapping(target = "model", source = "vehiculeRequestDto.model")
    @Mapping(target = "color", source = "vehiculeRequestDto.color")
    @Mapping(target = "id", ignore = true)
    Motorcycle toMotorcycle(MotorcycleRequestDto motorcycleRequestDto);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "color", source = "color")
    VehiculeResponseDto toVehiculeResponseDto(Motorcycle motorcycle);

    @Mapping(target = "vehiculeResponseDto", source = ".")
    MotorcycleResponseDto toMotorcycleResponseDto(Motorcycle motorcycle);
}

