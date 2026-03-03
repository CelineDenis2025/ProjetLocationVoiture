package com.accenture.model;

import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.RecreationalTypes;
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
public class Recreational extends Vehicule{

    private int nbPlace;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private boolean airConditioning;
    private float weight;
    private float height;
    private int nbBerths;
    private boolean providedKitchenEquipment;
    private boolean providedBedding;
    private boolean refregiratorEquipment;
    private boolean showerEquipment;
    @Enumerated(EnumType.STRING)
    private RecreationalTypes recreationalTypes;
//    @Enumerated(EnumType.STRING)
//    private Licences licence;
//    private List<Accessories> accessories;

}
