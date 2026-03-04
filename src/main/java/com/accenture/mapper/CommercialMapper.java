package com.accenture.mapper;

import com.accenture.model.Car;
import com.accenture.model.Commercial;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommercialMapper {

    @Mapping(target = "brand", source = "vehiculeRequestDto.brand")
    @Mapping(target = "model", source = "vehiculeRequestDto.model")
    @Mapping(target = "color", source = "vehiculeRequestDto.color")
    @Mapping(target = "id", ignore = true)
    Commercial toCommercial(CommercialRequestDto commercialRequestDto);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "color", source = "color")
    VehiculeResponseDto toVehiculeResponseDto(Commercial commercial);

    @Mapping(target = "vehiculeResponseDto", source = ".")
    CommercialResponsedto toCommercialResponseDto(Commercial commercial);
}
