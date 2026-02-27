package com.accenture.model;

import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.Licence;
import com.accenture.model.enums.RecreationalTypes;
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
public class Recreational extends Vehicule{

    private int nbPlace;
    private FuelType fuelType;
    private Transmission transmission;
    private boolean airConditioning;
    private float weight;
    private float height;
    private int nbBerths;
    private boolean providedKitchenEquipment;
    private boolean providedBedding;
    private boolean refregiratorEquipment;
    private boolean showerEquipment;
    private RecreationalTypes recreationalTypes;
    private Licence  licence;
//    private List<Accessories> accessories;

}
