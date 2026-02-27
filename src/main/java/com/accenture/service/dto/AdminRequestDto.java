package com.accenture.service.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminRequestDto(
        @NotBlank(message = "admin.function.null")
        String function
) {
}
