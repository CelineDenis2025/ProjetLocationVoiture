package com.accenture.service.dto;

import com.accenture.utils.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConnectedUserRequestDto(

        @NotBlank(message = Messages.CONNECTED_USER_FIRSTNAME_NULL)
        String firstName,

        @NotBlank(message = Messages.CONNECTED_USER_LASTNAME_NULL)
        String lastName,

        @NotBlank(message = Messages.CONNECTED_USER_EMAIL_NULL)
        @Email(message = Messages.CONNECTED_USER_EMAIL_INVALID)
        String email,

        @NotBlank(message = Messages.CONNECTED_USER_PASSWORD_NULL)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$",
                message = Messages.CONNECTED_USER_PASSWORD_INVALID)
        String password
) {
}
