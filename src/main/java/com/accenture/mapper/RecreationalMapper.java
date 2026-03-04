package com.accenture.mapper;

import com.accenture.model.Car;
import com.accenture.model.Recreational;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecreationalMapper {

    @Mapping(target = "brand", source = "vehiculeRequestDto.brand")
    @Mapping(target = "model", source = "vehiculeRequestDto.model")
    @Mapping(target = "color", source = "vehiculeRequestDto.color")
    @Mapping(target = "id", ignore = true)
    Recreational toRecreational(RecreationalRequestDto requestDto);

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "color", source = "color")
    VehiculeResponseDto toVehiculeResponseDto(Recreational recreational);

    @Mapping(target = "vehiculeResponseDto", source = ".")
    RecreationalResponseDto toRecreationalResponseDto(Recreational recreational);
}
