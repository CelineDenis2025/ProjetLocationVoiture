package com.accenture.service.dto;

public record AdminResponseDto(

        int id,

        ConnectedUserResponseDto connectedUserResponseDto,

        String function
) {
}
