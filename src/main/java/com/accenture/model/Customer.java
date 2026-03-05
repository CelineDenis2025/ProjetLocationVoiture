package com.accenture.model;

import com.accenture.model.enums.Licenses;
import com.accenture.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer extends ConnectedUser {

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private LocalDate dateOfBirth;

    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Licenses> licenses = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean inactive;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
    }
}
