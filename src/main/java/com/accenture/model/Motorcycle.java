package com.accenture.model;

import com.accenture.model.enums.Licences;
import com.accenture.model.enums.MotorcycleTypes;
import com.accenture.model.enums.Transmission;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Motorcycle extends Vehicule{

    private int nbCylinders;
    private float engineDisplacement;
    private float weight;
    private float enginePower;
    private float seatHeight;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private MotorcycleTypes  motorcycleTypes;
//    @Enumerated(EnumType.STRING)
//    private Licences licence;
//    private List<Accessories> accessories;


}
