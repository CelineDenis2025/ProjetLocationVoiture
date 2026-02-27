package com.accenture.mapper;

import com.accenture.model.Car;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    Car toCar(CarRequestDto carRequestDto);

    CarResponseDto toCarResponseDto(Car car);
}
