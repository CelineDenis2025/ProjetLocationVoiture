package com.accenture.service;

import com.accenture.exception.CarException;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;

import java.util.List;

public interface CarService {

    CarResponseDto addCar(CarRequestDto carRequestDto) throws CarException;
    List<CarResponseDto> findAllCars();
    CarResponseDto findById(int id);
    CarResponseDto partiallyUpdateCar(int idCar, CarRequestDto carRequestDto);
    void deleteCar(int idCar) throws CarException;

}
