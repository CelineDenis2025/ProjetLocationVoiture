package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.CarMapper;
import com.accenture.model.Car;
import com.accenture.repository.CarDao;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import com.accenture.service.dto.VehiculeRequestDto;
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
public class CarServiceImpl implements CarService {

    private final CarDao carDao;
    private final CarMapper carMapper;
    private final MessageSourceAccessor messages;

    @Override
    public CarResponseDto addCar(CarRequestDto carRequestDto) throws VehiculeException {
        verify(carRequestDto);
        Car car = carMapper.toCar(carRequestDto);
        Car saved = carDao.save(car);
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
        Car car = validateCar(id);
        return carMapper.toCarResponseDto(car);
    }

    @Override
    public CarResponseDto partiallyUpdateCar(int idCar, CarRequestDto carRequestDto) {
        Car car = validateCar(idCar);

        VehiculeRequestDto vehiculeRequestDto = carRequestDto.vehiculeRequestDto();
        if (vehiculeRequestDto != null) {
            if(vehiculeRequestDto.brand() != null && !vehiculeRequestDto.brand().isBlank()) {
                car.setBrand(vehiculeRequestDto.brand());
            }
            if(vehiculeRequestDto.model() != null && !vehiculeRequestDto.model().isBlank()) {
                car.setModel(vehiculeRequestDto.model());
            }
            if(vehiculeRequestDto.color() != null && !vehiculeRequestDto.color().isBlank()) {
                car.setColor(vehiculeRequestDto.color());
            }
        }

        if (carRequestDto.nbPlaces() != null) {
            car.setNbPlaces(carRequestDto.nbPlaces());
        }
        if (carRequestDto.fuelType() != null) {
            car.setFuelType(carRequestDto.fuelType());
        }
        if (carRequestDto.nbDoors() != null) {
            car.setNbDoors(carRequestDto.nbDoors());
        }
        if (carRequestDto.transmission() != null) {
            car.setTransmission(carRequestDto.transmission());
        }
        if (carRequestDto.airConditioning() != null) {
            car.setAirConditioning(carRequestDto.airConditioning());
        }
        if (carRequestDto.nbLuggage() != null) {
            car.setNbLuggage(carRequestDto.nbLuggage());
        }
        if (carRequestDto.carTypes() != null) {
            car.setCarTypes(carRequestDto.carTypes());
        }
        return carMapper.toCarResponseDto(car);
    }

    @Override
    public void deleteCar(int idCar) throws VehiculeException {
        validateCar(idCar);
        carDao.deleteById(idCar);
    }

    private void verify(CarRequestDto carRequestDto) {
        if (carRequestDto == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_NULL));
        }
        if (carRequestDto.vehiculeRequestDto().brand() == null || carRequestDto.vehiculeRequestDto().brand().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_BRAND_NULL));
        }
        if (carRequestDto.vehiculeRequestDto().model() == null || carRequestDto.vehiculeRequestDto().model().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_MODEL_NULL));
        }
        if (carRequestDto.vehiculeRequestDto().color() == null || carRequestDto.vehiculeRequestDto().color().isBlank()) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_COLOR_NULL));
        }
        if (carRequestDto.nbPlaces() == null || carRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_NB_PLACES_NULL));
        }
        if (carRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_FUEL_TYPE_NULL));
        }
        if (carRequestDto.nbDoors() == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_NB_DOORS_NULL));
        }
        if (carRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_TRANSMISSION_NULL));
        }
        if (carRequestDto.airConditioning() == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_AIR_CONDITIONING_NULL));
        }
        if (carRequestDto.nbLuggage() == null || carRequestDto.nbLuggage() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_NB_LUGGAGE_NULL));
        }
        if (carRequestDto.carTypes() == null) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_TYPE_NULL));
        }
    }


    private Car validateCar(int idCar) {
        Optional<Car> carOpt = carDao.findById(idCar);
        if (carOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage(Messages.CAR_ID_NOT_FOUND));
        }
        return carOpt.get();
    }
}