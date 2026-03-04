package com.accenture.mapper;

import com.accenture.model.Bike;
import com.accenture.model.Car;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "brand", source = "vehiculeRequestDto.brand")
    @Mapping(target = "model", source = "vehiculeRequestDto.model")
    @Mapping(target = "color", source = "vehiculeRequestDto.color")
    @Mapping(target = "id", ignore = true)
    Car toCar(CarRequestDto carRequestDto);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "color", source = "color")
    VehiculeResponseDto toVehiculeResponseDto(Car car);

    @Mapping(target = "vehiculeResponseDto", source = ".")
    CarResponseDto toCarResponseDto(Car car);
}
