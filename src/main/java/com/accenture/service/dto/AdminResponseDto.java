package com.accenture.service.dto;

public record AdminResponseDto(

        int id,

        String firstName,
        String lastName,
        String email,
        String function
) {
}
