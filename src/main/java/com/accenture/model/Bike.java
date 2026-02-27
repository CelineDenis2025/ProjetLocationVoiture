package com.accenture.model;

import com.accenture.model.enums.BikeTypes;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Bike extends Vehicule{

    private double frameSize;
    private float weight;
    private boolean electric;
    private boolean discBrake;
    private BikeTypes bikeTypes;
//    private List<Accessories> accessories;



}
