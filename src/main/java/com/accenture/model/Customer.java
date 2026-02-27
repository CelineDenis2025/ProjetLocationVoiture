package com.accenture.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Data
@Entity
public class Customer extends ConnectedUser {

    private String street;
    private String postalCode;
    private String city;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private boolean inactive;


}
