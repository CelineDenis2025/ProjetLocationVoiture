package com.accenture.mapper;

import com.accenture.model.Motorcycle;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MotorcycleMapper {

    @Mapping(target = "id", ignore = true)
    Motorcycle toMotorcycle(MotorcycleRequestDto motorcycleRequestDto);

    MotorcycleResponseDto toMotorcycleResponseDto(Motorcycle motorcycle);
}
