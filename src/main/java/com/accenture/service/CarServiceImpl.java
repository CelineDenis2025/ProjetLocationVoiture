package com.accenture.service;

import com.accenture.exception.CarException;
import com.accenture.mapper.CarMapper;
import com.accenture.model.Car;
import com.accenture.model.enums.*;
import com.accenture.repository.CarDao;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

    private final CarDao carDao;
    private final CarMapper carMapper;
    private final MessageSourceAccessor messages;

    @Override
    public CarResponseDto addCar(CarRequestDto carRequestDto) throws CarException {
        verify(carRequestDto);
        Car car = carMapper.toCar(carRequestDto);
//        car.setLicence(calculateLicence(car.getNbPlaces()));
        Car saved =  carDao.save(car);
        return carMapper.toCarResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarResponseDto> findAllCars() {
        List<Car> cars = carDao.findAll();
        return cars.stream()
                .map(carMapper::toCarResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CarResponseDto findById(int id) {
        Optional<Car> carOpt = carDao.findById(id);
        if (carOpt.isEmpty()) {
            throw new CarException(messages.getMessage("car.id.notfound"));
        }
        return carMapper.toCarResponseDto(carOpt.get());
    }

    @Override
    public CarResponseDto partiallyUpdateCar(int idCar, CarRequestDto carRequestDto) {
        Optional<Car> carOpt = carDao.findById(idCar);
        if (carOpt.isEmpty()) {
            throw new CarException(messages.getMessage("car.id.notfound"));
        }
        Car car = carOpt.get();
        if (carRequestDto.brand() != null && !carRequestDto.brand().isBlank()){
            car.setBrand(carRequestDto.brand());
        }
        if (carRequestDto.model() != null && !carRequestDto.model().isBlank()){
            car.setModel(carRequestDto.model());
        }
        if (carRequestDto.color() != null && !carRequestDto.color().isBlank()){
            car.setColor(carRequestDto.color());
        }
        if (carRequestDto.fuelType() != null){
            car.setFuelType(carRequestDto.fuelType());
        }
        if (carRequestDto.nbDoors() != null){
            car.setNbDoors(carRequestDto.nbDoors());
        }
        if (carRequestDto.transmission() != null){
            car.setTransmission(carRequestDto.transmission());
        }
        if (carRequestDto.airConditioning() != null){
            car.setAirConditioning(carRequestDto.airConditioning());
        }
        if (carRequestDto.nbLunggage() != null){
            car.setNbLunggage(carRequestDto.nbLunggage());
        }
        if (carRequestDto.carTypes() != null){
            car.setCarTypes(carRequestDto.carTypes());
        }
        return carMapper.toCarResponseDto(car);
    }

    @Override
    public void deleteCar(int idCar) throws CarException {
        Optional<Car> carOpt = carDao.findById(idCar);
        if (carOpt.isEmpty()) {
            throw new CarException(messages.getMessage("car.id.notfound"));
        }
        carDao.deleteById(idCar);
    }

    private void verify(CarRequestDto carRequestDto) {
        if (carRequestDto == null) {
            throw new CarException(messages.getMessage("car.null"));
        }
        if (carRequestDto.nbPlaces() == null || carRequestDto.nbPlaces() < 0) {
            throw new CarException(messages.getMessage("car.nbPlaces.null"));
        }
        if (carRequestDto.fuelType() == null) {
            throw new CarException(messages.getMessage("car.fuelType.null"));
        }
        if (carRequestDto.nbDoors() ==  null) {
            throw new CarException(messages.getMessage("car.nbDoors.null"));
        }
        if (carRequestDto.transmission() ==  null) {
            throw new CarException(messages.getMessage("car.transmission.null"));
        }
        if (carRequestDto.airConditioning() ==  null) {
            throw new CarException(messages.getMessage("car.airConditioning.null"));
        }
        if (carRequestDto.nbLunggage() ==  null ||  carRequestDto.nbLunggage() < 0) {
            throw new CarException(messages.getMessage("car.nbLunggage.null"));
        }
        if (carRequestDto.carTypes() ==  null) {
            throw new CarException(messages.getMessage("car.carTypes.null"));
        }
    }

    private String calculateLicence(int nbPlaces) {
        if (nbPlaces <= 9){
            return messages.getMessage("car.licence.b");
        }
        else if (nbPlaces <= 16){
            return messages.getMessage("car.licence.d1");
        }
        else {
            return messages.getMessage("car.licence.invalid");
        }
    }
}
