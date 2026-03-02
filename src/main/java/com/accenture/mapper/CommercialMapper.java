package com.accenture.mapper;

import com.accenture.model.Commercial;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommercialMapper {

    @Mapping(target = "id", ignore = true)
    Commercial toCommercial(CommercialRequestDto commercialRequestDto);

    CommercialResponsedto toCommercialResponseDto(Commercial commercial);
}
