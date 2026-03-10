package com.accenture.service.dto;

import com.accenture.utils.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AdminRequestDto(

        @Valid
        ConnectedUserRequestDto connectedUserRequestDto,

        @NotBlank(message = Messages.ADMIN_FUNCTION_NULL)
        String function
) {
}
