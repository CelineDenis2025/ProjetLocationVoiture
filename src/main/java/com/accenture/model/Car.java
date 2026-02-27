package com.accenture.model;

import com.accenture.model.enums.*;
import jakarta.persistence.Entity;
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
    private FuelType fuelType;
    private NbDoors nbDoors;
    private Transmission transmission;
    private boolean airConditioning;
    private int nbLunggage;
    private CarTypes carTypes;
    private Licence licence;
//    private List<Accessories> accessories;


}
