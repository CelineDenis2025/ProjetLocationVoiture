package com.accenture.service;

import com.accenture.exception.MotorcycleException;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;

import java.util.List;

public interface MotorcycleService {

    MotorcycleResponseDto addMotorcycle(MotorcycleRequestDto motorcycleRequestDto) throws MotorcycleException;
    List<MotorcycleResponseDto> findAllMotorcycles();
    MotorcycleResponseDto findById(int id);
    MotorcycleResponseDto partiallyUpdateMotorcycle(int idMotorcycle, MotorcycleRequestDto motorcycleRequestDto);
    void deleteMotorcycle(int idMotorcycle) throws MotorcycleException;
}
