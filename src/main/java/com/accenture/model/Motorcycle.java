package com.accenture.model;

import com.accenture.model.enums.Licence;
import com.accenture.model.enums.MotorcycleTypes;
import com.accenture.model.enums.Transmission;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Motorcycle extends Vehicule{

    private int nbCylinders;
    private int engineDisplacement;
    private float weight;
    private float enginePower;
    private float seatHeight;
    private Transmission transmission;
    private MotorcycleTypes  motorcycleTypes;
    private Licence licence;
//    private List<Accessories> accessories;


}
