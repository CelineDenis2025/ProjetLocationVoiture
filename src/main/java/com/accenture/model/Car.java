package com.accenture.model;

import com.accenture.model.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Car extends Vehicule{

    private int nbPlaces;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private NbDoors nbDoors;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private boolean airConditioning;
    private int nbLunggage;
    @Enumerated(EnumType.STRING)
    private CarTypes carTypes;
//    @Enumerated(EnumType.STRING)
//    private Licences licence;
//    private List<Accessories> accessories;


}
