package com.accenture.mapper;

import com.accenture.model.Bike;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BikeMapper {

    @Mapping(target = "id", ignore = true)
    Bike toBike(BikeRequestDto bikeRequestDto);

    BikeResponseDto toBikeResponseDto(Bike bike);
}
