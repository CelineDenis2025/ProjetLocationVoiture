package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;

import java.util.List;

public interface BikeService {

    BikeResponseDto addBike(BikeRequestDto bikeRequestDto) throws VehiculeException;
    List<BikeResponseDto> findAllBikes();
    BikeResponseDto findById(int id);
    BikeResponseDto partiallyUpdateBike(int idBike, BikeRequestDto bikeRequestDto);
    void deleteBike(int idBike) throws VehiculeException;
}
