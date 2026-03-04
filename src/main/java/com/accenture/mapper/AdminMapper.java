package com.accenture.mapper;

import com.accenture.model.Admin;
import com.accenture.model.Bike;
import com.accenture.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(target = "role", constant = "ADMIN")
    @Mapping(target = "firstName", source = "connectedUserRequestDto.firstName")
    @Mapping(target = "lastName", source = "connectedUserRequestDto.lastName")
    @Mapping(target = "email", source = "connectedUserRequestDto.email")
    @Mapping(target = "id", ignore = true)
    Admin toAdmin(AdminRequestDto adminRequestDto);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    ConnectedUserResponseDto toConnectedUserResponseDto(Admin admin);

    @Mapping(target = "connectedUserResponseDto", source = ".")
    AdminResponseDto toAdminResponseDto(Admin admin);
}
