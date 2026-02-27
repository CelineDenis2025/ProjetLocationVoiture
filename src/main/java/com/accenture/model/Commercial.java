package com.accenture.model;

import com.accenture.model.enums.CommercialTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.Licence;
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
public class Commercial extends Vehicule{

    private int nbPlaces;
    private FuelType fuelType;
    private Transmission transmission;
    private boolean airConditioning;
    private float maximalLoad;
    private float weight;
    private float capacity;
    private CommercialTypes  commercialTypes;
    private Licence licence;
//    private List<Accessories> accessories;

}
