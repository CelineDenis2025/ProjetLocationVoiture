package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.BikeMapper;
import com.accenture.model.Bike;
import com.accenture.repository.BikeDao;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
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
        Bike Saved = bikeDao.save(bike);
        return bikeMapper.toBikeResponseDto(Saved);
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
        Optional<Bike> bikeOptional = bikeDao.findById(id);
        if (bikeOptional.isEmpty()) {
            throw new VehiculeException(messages.getMessage("bike.id.not.found"));
        }
        return bikeMapper.toBikeResponseDto(bikeOptional.get());
    }


    @Override
    public BikeResponseDto partiallyUpdateBike(int idBike, BikeRequestDto bikeRequestDto) {
        Optional<Bike> bikeOptional = bikeDao.findById(idBike);
        if (bikeOptional.isEmpty()) {
            throw new VehiculeException(messages.getMessage("bike.id.not.found"));
        }

        Bike bike = bikeOptional.get();

        if (bikeRequestDto.brand() != null && !bikeRequestDto.brand().isBlank()) {
            bike.setBrand(bikeRequestDto.brand());
        }
        if (bikeRequestDto.model() != null && !bikeRequestDto.model().isBlank()) {
            bike.setModel(bikeRequestDto.model());
        }
        if (bikeRequestDto.color() != null && !bikeRequestDto.color().isBlank()) {
            bike.setColor(bikeRequestDto.color());
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
        Optional<Bike> bikeOptional = bikeDao.findById(idBike);
        if (bikeOptional.isEmpty()) {
            throw new VehiculeException(messages.getMessage("bike.id.not.found"));
        }
        bikeDao.deleteById(idBike);
    }


    private void verify(BikeRequestDto bikeRequestDto) {
        if (bikeRequestDto == null) {
            throw new VehiculeException(messages.getMessage("bike.null"));
        }
        if (bikeRequestDto.brand() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.brand.null"));
        }
        if (bikeRequestDto.model() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.model.null"));
        }
        if (bikeRequestDto.color() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.color.null"));
        }
        if (bikeRequestDto.frameSize() == null ||  bikeRequestDto.frameSize() < 0 || bikeRequestDto.frameSize().isNaN()) {
            throw new VehiculeException(messages.getMessage("bike.frameSize.null"));
        }
        if (bikeRequestDto.weight() == null ||  bikeRequestDto.weight() < 0 || bikeRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage("bike.weight.null"));
        }
        if (bikeRequestDto.electric()) {
            if(bikeRequestDto.batteryCapacity() == null || bikeRequestDto.batteryCapacity() < 0 || bikeRequestDto.batteryCapacity().isNaN()) {
                throw new VehiculeException(messages.getMessage("bike.battery.capacity.null"));
            }
            if (bikeRequestDto.autonomy() == null || bikeRequestDto.autonomy() < 0 ||  bikeRequestDto.autonomy().isNaN()) {
                throw new VehiculeException(messages.getMessage("bike.autonomy.null"));
            }
        } else {

            if (bikeRequestDto.batteryCapacity() != null || bikeRequestDto.autonomy() != null) {
                throw new VehiculeException(messages.getMessage("bike.electric.fields.not.allowed"));
            }
        }
        if (bikeRequestDto.discBrake() == null) {
            throw new VehiculeException(messages.getMessage("bike.discBrake.null"));
        }
        if (bikeRequestDto.bikeTypes() == null) {
            throw new VehiculeException(messages.getMessage("bike.bikeType.null"));
        }
    }
}
