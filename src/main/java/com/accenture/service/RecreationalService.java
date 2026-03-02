package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;

import java.util.List;

public interface RecreationalService {

    RecreationalResponseDto addRecreational(RecreationalRequestDto recreationalRequestDto) throws VehiculeException;
    List<RecreationalResponseDto> findAllRecreationals();
    RecreationalResponseDto findById(int id);
    RecreationalResponseDto partiallyUpdateRecreational(int idRecreational, RecreationalRequestDto recreationalRequestDto);
    void deleteRecreational(int idRecreational) throws VehiculeException;
}
