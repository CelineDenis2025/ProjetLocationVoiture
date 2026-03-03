package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.CarMapper;
import com.accenture.model.Car;
import com.accenture.repository.CarDao;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
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
    public CarResponseDto addCar(CarRequestDto carRequestDto) throws VehiculeException {
        verify(carRequestDto);
        Car car = carMapper.toCar(carRequestDto);
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
            throw new VehiculeException(messages.getMessage("car.id.not.found"));
        }
        return carMapper.toCarResponseDto(carOpt.get());
    }

    @Override
    public CarResponseDto partiallyUpdateCar(int idCar, CarRequestDto carRequestDto) {
        Optional<Car> carOpt = carDao.findById(idCar);
        if (carOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("car.id.not.found"));
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
        if (carRequestDto.nbPlaces() != null){
            car.setNbPlaces(carRequestDto.nbPlaces());
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
    public void deleteCar(int idCar) throws VehiculeException {
        Optional<Car> carOpt = carDao.findById(idCar);
        if (carOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("car.id.not.found"));
        }
        carDao.deleteById(idCar);
    }

    private void verify(CarRequestDto carRequestDto) {
        if (carRequestDto == null) {
            throw new VehiculeException(messages.getMessage("car.null"));
        }
        if (carRequestDto.brand() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.brand.null"));
        }
        if (carRequestDto.model() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.model.null"));
        }
        if (carRequestDto.color() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.color.null"));
        }
        if (carRequestDto.nbPlaces() == null || carRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage("car.nbPlaces.null"));
        }
        if (carRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage("car.fuelType.null"));
        }
        if (carRequestDto.nbDoors() ==  null) {
            throw new VehiculeException(messages.getMessage("car.nbDoors.null"));
        }
        if (carRequestDto.transmission() ==  null) {
            throw new VehiculeException(messages.getMessage("car.transmission.null"));
        }
        if (carRequestDto.airConditioning() ==  null) {
            throw new VehiculeException(messages.getMessage("car.airConditioning.null"));
        }
        if (carRequestDto.nbLunggage() ==  null ||  carRequestDto.nbLunggage() < 0) {
            throw new VehiculeException(messages.getMessage("car.nbLunggage.null"));
        }
        if (carRequestDto.carTypes() ==  null) {
            throw new VehiculeException(messages.getMessage("car.carTypes.null"));
        }
    }
}