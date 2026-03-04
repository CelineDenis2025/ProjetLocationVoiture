package com.accenture.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConnectedUserRequestDto(

        @NotBlank(message = "connectedUser.firstName.null")
        String firstName,

        @NotBlank(message = "connectedUser.lastName.null")
        String lastName,

        @NotBlank(message = "connectedUser.email.null")
        @Email(message = "connectedUser.email.invalid")
        String email,

        @NotBlank(message = "connectedUser.password.null")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$",
                message = "connectedUser.password.invalid")
        String password
) {
}
