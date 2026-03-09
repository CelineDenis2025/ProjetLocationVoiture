package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.MotorcycleMapper;
import com.accenture.model.Commercial;
import com.accenture.model.Motorcycle;
import com.accenture.repository.MotorcycleDao;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
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
public class MotorcycleServiceImpl implements MotorcycleService {

    private final MotorcycleDao motorcycleDao;
    private final MotorcycleMapper motorcycleMapper;
    private final MessageSourceAccessor messages;


    @Override
    public MotorcycleResponseDto addMotorcycle(MotorcycleRequestDto motorcycleRequestDto) throws VehiculeException {
        verify(motorcycleRequestDto);
        Motorcycle motorcycle = motorcycleMapper.toMotorcycle(motorcycleRequestDto);
        Motorcycle saved = motorcycleDao.save(motorcycle);
        return motorcycleMapper.toMotorcycleResponseDto(saved);
    }


    @Transactional(readOnly = true)
    @Override
    public List<MotorcycleResponseDto> findAllMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleDao.findAll();
        return motorcycles.stream()
                .map(motorcycleMapper::toMotorcycleResponseDto)
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public MotorcycleResponseDto findById(int id) {
        Motorcycle motorcycle = validateMotorcycle(id);
        return motorcycleMapper.toMotorcycleResponseDto(motorcycle);
    }


    @Override
    public MotorcycleResponseDto partiallyUpdateMotorcycle(int idMotorcycle, MotorcycleRequestDto motorcycleRequestDto) {
        Motorcycle motorcycle = validateMotorcycle(idMotorcycle);

        if (motorcycleRequestDto.vehiculeRequestDto().brand() != null && !motorcycleRequestDto.vehiculeRequestDto().brand().isBlank()) {
            motorcycle.setBrand(motorcycleRequestDto.vehiculeRequestDto().brand());
        }
        if (motorcycleRequestDto.vehiculeRequestDto().model() != null && !motorcycleRequestDto.vehiculeRequestDto().model().isBlank()) {
            motorcycle.setModel(motorcycleRequestDto.vehiculeRequestDto().model());
        }
        if (motorcycleRequestDto.vehiculeRequestDto().color() != null && !motorcycleRequestDto.vehiculeRequestDto().color().isBlank()) {
            motorcycle.setColor(motorcycleRequestDto.vehiculeRequestDto().color());
        }
        if (motorcycleRequestDto.nbCylinders() != null) {
            motorcycle.setNbCylinders(motorcycleRequestDto.nbCylinders());
        }
        if (motorcycleRequestDto.engineDisplacement() != null) {
            motorcycle.setEngineDisplacement(motorcycleRequestDto.engineDisplacement());
        }
        if (motorcycleRequestDto.weight() != null) {
            motorcycle.setWeight(motorcycleRequestDto.weight());
        }
        if (motorcycleRequestDto.enginePower() != null) {
            motorcycle.setEnginePower(motorcycleRequestDto.enginePower());
        }
        if (motorcycleRequestDto.seatHeight() != null) {
            motorcycle.setSeatHeight(motorcycleRequestDto.seatHeight());
        }
        if (motorcycleRequestDto.transmission() != null) {
            motorcycle.setTransmission(motorcycleRequestDto.transmission());
        }
        if (motorcycleRequestDto.motorcycleTypes() != null) {
            motorcycle.setMotorcycleTypes(motorcycleRequestDto.motorcycleTypes());
        }
        return motorcycleMapper.toMotorcycleResponseDto(motorcycle);
    }


    @Override
    public void deleteMotorcycle(int idMotorcycle) throws VehiculeException {
        validateMotorcycle(idMotorcycle);
        motorcycleDao.deleteById(idMotorcycle);
    }


    private void verify(MotorcycleRequestDto motorcycleRequestDto) {
        if (motorcycleRequestDto == null) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_NULL));
        }
        if (motorcycleRequestDto.vehiculeRequestDto().brand() == null || motorcycleRequestDto.vehiculeRequestDto().brand().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_BRAND_NULL));
        }
        if (motorcycleRequestDto.vehiculeRequestDto().model() == null || motorcycleRequestDto.vehiculeRequestDto().model().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_MODEL_NULL));
        }
        if (motorcycleRequestDto.vehiculeRequestDto().color() == null || motorcycleRequestDto.vehiculeRequestDto().color().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_COLOR_NULL));
        }
        if (motorcycleRequestDto.nbCylinders() == null || motorcycleRequestDto.nbCylinders() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_NB_CYLINDERS_NULL));
        }
        if (motorcycleRequestDto.engineDisplacement() == null || motorcycleRequestDto.engineDisplacement() < 0 || motorcycleRequestDto.engineDisplacement().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_ENGINE_DISPLACEMENT_NULL));
        }
        if (motorcycleRequestDto.weight() == null || motorcycleRequestDto.weight() < 0 || motorcycleRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_WEIGHT_NULL));
        }
        if (motorcycleRequestDto.enginePower() == null || motorcycleRequestDto.enginePower() < 0 || motorcycleRequestDto.enginePower().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_ENGINE_POWER_NULL));
        }
        if (motorcycleRequestDto.seatHeight() == null || motorcycleRequestDto.seatHeight() < 0 || motorcycleRequestDto.seatHeight().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_SEAT_HEIGHT_NULL));
        }
        if (motorcycleRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_TRANSMISSION_NULL));
        }
        if (motorcycleRequestDto.motorcycleTypes() == null) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_TYPE_NULL));
        }
    }


    private Motorcycle validateMotorcycle(int idMotorcycle) {
        Optional<Motorcycle> motorcycleOpt = motorcycleDao.findById(idMotorcycle);
        if (motorcycleOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage(Messages.MOTORCYCLE_ID_NOT_FOUND));
        }
        return motorcycleOpt.get();
    }
}
