package com.accenture.model;

import com.accenture.model.enums.Licence;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public abstract class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String brand;
    protected String model;
    protected String color;
    protected Licence licence;
    protected float dailyBaseRentalRate;
    protected float mileage;
    protected boolean active;
    protected boolean removedFromTheFleet;


}
