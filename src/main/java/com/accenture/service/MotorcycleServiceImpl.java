package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.MotorcycleMapper;
import com.accenture.model.Motorcycle;
import com.accenture.repository.MotorcycleDao;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
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
        Optional<Motorcycle> motorcycleOpt = motorcycleDao.findById(id);
        if (motorcycleOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("motorcyle.id.not.found"));
        }
        return motorcycleMapper.toMotorcycleResponseDto(motorcycleOpt.get());
    }


    @Override
    public MotorcycleResponseDto partiallyUpdateMotorcycle(int idMotorcycle, MotorcycleRequestDto motorcycleRequestDto) {
        Optional<Motorcycle> motorcycleOpt = motorcycleDao.findById(idMotorcycle);
        if (motorcycleOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("motorcycle.id.not.found"));
        }
        Motorcycle motorcycle = motorcycleOpt.get();
        if (motorcycleRequestDto.brand() != null && !motorcycleRequestDto.brand().isBlank()) {
            motorcycle.setBrand(motorcycleRequestDto.brand());
        }
        if (motorcycleRequestDto.model() != null && !motorcycleRequestDto.model().isBlank()) {
            motorcycle.setModel(motorcycleRequestDto.model());
        }
        if (motorcycleRequestDto.color() != null && !motorcycleRequestDto.color().isBlank()) {
            motorcycle.setColor(motorcycleRequestDto.color());
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
        Optional<Motorcycle> motorcycleOpt = motorcycleDao.findById(idMotorcycle);
        if (motorcycleOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("motorcycle.id.not.found"));
        }
        motorcycleDao.deleteById(idMotorcycle);
    }


    private void verify(MotorcycleRequestDto motorcycleRequestDto) {
        if (motorcycleRequestDto == null) {
            throw new VehiculeException(messages.getMessage("motorcycle.null"));
        }
        if (motorcycleRequestDto.nbCylinders() == null || motorcycleRequestDto.nbCylinders() < 0) {
            throw new VehiculeException(messages.getMessage("motorcyle.nbCylinders.null"));
        }
        if (motorcycleRequestDto.engineDisplacement() == null || motorcycleRequestDto.engineDisplacement() < 0 ||motorcycleRequestDto.engineDisplacement().isNaN()) {
            throw new VehiculeException(messages.getMessage("motorcyle.engineDisplacement.null"));
        }
        if (motorcycleRequestDto.weight() == null || motorcycleRequestDto.weight() < 0 || motorcycleRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage("motorcyle.weight.null"));
        }
        if (motorcycleRequestDto.enginePower() == null || motorcycleRequestDto.enginePower() < 0 || motorcycleRequestDto.enginePower().isNaN()) {
            throw new VehiculeException(messages.getMessage("motorcyle.enginePower.null"));
        }
        if (motorcycleRequestDto.seatHeight() == null || motorcycleRequestDto.seatHeight() < 0 || motorcycleRequestDto.seatHeight().isNaN()) {
            throw new VehiculeException(messages.getMessage("motorcyle.seatHeight.null"));
        }
        if (motorcycleRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage("motorcyle.transmission.null"));
        }
        if (motorcycleRequestDto.motorcycleTypes() == null) {
            throw new VehiculeException(messages.getMessage("motorcyle.motorcycleTypes.null"));
        }
    }
}

