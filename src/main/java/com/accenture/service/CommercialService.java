package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;

import java.util.List;

public interface CommercialService {

    CommercialResponsedto addCommercial(CommercialRequestDto commercialRequestDto) throws VehiculeException;
    List<CommercialResponsedto> findAllCommercials();
    CommercialResponsedto findById(int id);
    CommercialResponsedto partiallyUpdateCommercial(int idCommercial, CommercialRequestDto commercialRequestDto);
    void deleteCommercial(int idCommercial) throws VehiculeException;
}
