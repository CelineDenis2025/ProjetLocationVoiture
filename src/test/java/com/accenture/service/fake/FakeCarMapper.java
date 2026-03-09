package com.accenture.service.fake;

import com.accenture.mapper.CarMapper;
import com.accenture.model.Car;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import com.accenture.service.dto.VehiculeResponseDto;

import java.util.concurrent.atomic.AtomicInteger;

public class FakeCarMapper implements CarMapper {

    private final AtomicInteger seq = new AtomicInteger(1);

    @Override
    public Car toCar(CarRequestDto carRequestDto) {
        Car car = new Car();

        car.setId(seq.getAndIncrement());
        car.setBrand(carRequestDto.vehiculeRequestDto().brand());
        car.setColor(carRequestDto.vehiculeRequestDto().color());
        car.setModel(carRequestDto.vehiculeRequestDto().model());
        car.setNbPlaces(carRequestDto.nbPlaces());
        car.setFuelType(carRequestDto.fuelType());
        car.setNbDoors(carRequestDto.nbDoors());
        car.setTransmission(carRequestDto.transmission());
        car.setAirConditioning(carRequestDto.airConditioning());
        car.setNbLuggage(carRequestDto.nbLuggage());
        car.setCarTypes(carRequestDto.carTypes());

        return car;
    }

    @Override
    public VehiculeResponseDto toVehiculeResponseDto(Car car) {
        return new VehiculeResponseDto(
                car.getBrand(),
                car.getModel(),
                car.getColor()
        );
    }

    @Override
    public CarResponseDto toCarResponseDto(Car car) {
        VehiculeResponseDto vehiculeResponseDto = new VehiculeResponseDto(
                car.getBrand(),
                car.getModel(),
                car.getColor()
        );
        return new CarResponseDto(
                car.getId(),
                vehiculeResponseDto,
                car.getNbPlaces(),
                car.getFuelType(),
                car.getNbDoors(),
                car.getTransmission(),
                car.isAirConditioning(),
                car.getNbLuggage(),
                car.getCarTypes()
                );
    }
}
