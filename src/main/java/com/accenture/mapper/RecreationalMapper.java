package com.accenture.mapper;

import com.accenture.model.Motorcycle;
import com.accenture.model.Recreational;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecreationalMapper {

    @Mapping(target = "id", ignore = true)
    Recreational toRecreational(RecreationalRequestDto requestDto);

    RecreationalResponseDto toRecreationalResponseDto(Recreational recreational);
}
