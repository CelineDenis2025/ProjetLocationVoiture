package com.accenture.mapper;

import com.accenture.model.Admin;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "role", constant = "ADMIN")
    @Mapping(target = "id", ignore = true)
    Admin toAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto toAdminResponseDto(Admin admin);
}
