package com.accenture.model;

import com.accenture.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin extends ConnectedUser {

    private String function;

    @Enumerated(EnumType.STRING)
    private Role role;
}
