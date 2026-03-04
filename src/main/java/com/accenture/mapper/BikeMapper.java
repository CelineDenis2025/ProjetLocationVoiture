package com.accenture.mapper;

import com.accenture.model.Bike;
import com.accenture.model.Vehicule;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
import com.accenture.service.dto.VehiculeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BikeMapper {

    @Mapping(target = "brand", source = "vehiculeRequestDto.brand")
    @Mapping(target = "model", source = "vehiculeRequestDto.model")
    @Mapping(target = "color", source = "vehiculeRequestDto.color")
    @Mapping(target = "id", ignore = true)
    Bike toBike(BikeRequestDto bikeRequestDto);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "color", source = "color")
    VehiculeResponseDto toVehiculeResponseDto(Bike bike);

    @Mapping(target = "vehiculeResponseDto", source = ".")
    BikeResponseDto toBikeResponseDto(Bike bike);
}
