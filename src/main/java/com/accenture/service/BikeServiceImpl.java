package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.exception.VehiculeException;
import com.accenture.mapper.BikeMapper;
import com.accenture.model.Admin;
import com.accenture.model.Bike;
import com.accenture.model.Car;
import com.accenture.model.enums.Role;
import com.accenture.repository.BikeDao;
import com.accenture.service.dto.*;
import com.accenture.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BikeServiceImpl implements BikeService {

    private final BikeDao bikeDao;
    private final BikeMapper bikeMapper;
    private final MessageSourceAccessor messages;

    @Override
    public BikeResponseDto addBike(BikeRequestDto bikeRequestDto) throws VehiculeException {
        verify(bikeRequestDto);
        Bike bike = bikeMapper.toBike(bikeRequestDto);
        Bike saved = bikeDao.save(bike);
        return bikeMapper.toBikeResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BikeResponseDto> findAllBikes() {
        List<Bike> bikes = bikeDao.findAll();
        return bikes.stream()
                .map(bikeMapper::toBikeResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public BikeResponseDto findById(int id) {
        Bike bike = validateBike(id);
        return bikeMapper.toBikeResponseDto(bike);
    }

    @Override
    public BikeResponseDto partiallyUpdateBike(int idBike, BikeRequestDto bikeRequestDto) {
        Bike bike = validateBike(idBike);

        VehiculeRequestDto vehiculeRequestDto = bikeRequestDto.vehiculeRequestDto();
        if (vehiculeRequestDto != null) {
            if(vehiculeRequestDto.brand() != null && !vehiculeRequestDto.brand().isBlank()) {
                bike.setBrand(vehiculeRequestDto.brand());
            }
            if(vehiculeRequestDto.model() != null && !vehiculeRequestDto.model().isBlank()) {
                bike.setModel(vehiculeRequestDto.model());
            }
            if(vehiculeRequestDto.color() != null && !vehiculeRequestDto.color().isBlank()) {
                bike.setColor(vehiculeRequestDto.color());
            }
        }

        if (bikeRequestDto.frameSize() != null) {
            bike.setFrameSize(bikeRequestDto.frameSize());
        }
        if (bikeRequestDto.weight() != null) {
            bike.setWeight(bikeRequestDto.weight());
        }

        if (bikeRequestDto.electric() != null) {
            bike.setElectric(bikeRequestDto.electric());

            if (bikeRequestDto.electric()) {
                if (bikeRequestDto.batteryCapacity() != null) {
                    bike.setBatteryCapacity(bikeRequestDto.batteryCapacity());
                }
                if (bikeRequestDto.autonomy() != null) {
                    bike.setAutonomy(bikeRequestDto.autonomy());
                }
            } else {
                bike.setBatteryCapacity(null);
                bike.setAutonomy(null);
            }
        }

        if (bikeRequestDto.discBrake() != null) {
            bike.setDiscBrake(bikeRequestDto.discBrake());
        }
        if (bikeRequestDto.bikeTypes() != null) {
            bike.setBikeTypes(bikeRequestDto.bikeTypes());
        }

        return bikeMapper.toBikeResponseDto(bike);
    }

    @Override
    public void deleteBike(int idBike) throws VehiculeException {
        validateBike(idBike);
        bikeDao.deleteById(idBike);
    }


    private void verify(BikeRequestDto bikeRequestDto) {
        if (bikeRequestDto == null) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_NULL));
        }
        if (bikeRequestDto.vehiculeRequestDto().brand() == null || bikeRequestDto.vehiculeRequestDto().brand().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_BRAND_NULL));
        }
        if (bikeRequestDto.vehiculeRequestDto().model() == null || bikeRequestDto.vehiculeRequestDto().model().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_MODEL_NULL));
        }
        if (bikeRequestDto.vehiculeRequestDto().color() == null || bikeRequestDto.vehiculeRequestDto().color().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_COLOR_NULL));
        }
        if (bikeRequestDto.frameSize() == null || bikeRequestDto.frameSize() < 0 || bikeRequestDto.frameSize().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_FRAMESIZE_NULL));
        }
        if (bikeRequestDto.weight() == null || bikeRequestDto.weight() < 0 || bikeRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_WEIGHT_NULL));
        }
        if (bikeRequestDto.electric()) {
            if (bikeRequestDto.batteryCapacity() == null || bikeRequestDto.batteryCapacity() < 0 || bikeRequestDto.batteryCapacity().isNaN()) {
                throw new VehiculeException(messages.getMessage(Messages.BIKE_BATTERY_CAPACITY_NULL));
            }
            if (bikeRequestDto.autonomy() == null || bikeRequestDto.autonomy() < 0 || bikeRequestDto.autonomy().isNaN()) {
                throw new VehiculeException(messages.getMessage(Messages.BIKE_AUTONOMY_NULL));
            }
        } else {

            if (bikeRequestDto.batteryCapacity() != null || bikeRequestDto.autonomy() != null) {
                throw new VehiculeException(messages.getMessage(Messages.BIKE_ELECTRIC_FIELDS_NOT_ALLOWED));
            }
        }
        if (bikeRequestDto.discBrake() == null) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_DISCBRAKE_NULL));
        }
        if (bikeRequestDto.bikeTypes() == null) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_TYPE_NULL));
        }
    }


    private Bike validateBike(int idBike) {
        Optional<Bike> bikeOptional = bikeDao.findById(idBike);
        if (bikeOptional.isEmpty()) {
            throw new VehiculeException(messages.getMessage(Messages.BIKE_ID_NOT_FOUND));
        }
        return bikeOptional.get();
    }
}
