package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.CommercialMapper;
import com.accenture.model.Commercial;
import com.accenture.repository.CommercialDao;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CommercialServiceImpl implements CommercialService {

    private final CommercialDao  commercialDao;
    private final CommercialMapper commercialMapper;
    private final MessageSourceAccessor messages;


    @Override
    public CommercialResponsedto addCommercial(CommercialRequestDto commercialRequestDto) throws VehiculeException {
        verify(commercialRequestDto);
        Commercial commercial = commercialMapper.toCommercial(commercialRequestDto);
        Commercial saved =  commercialDao.save(commercial);
        return commercialMapper.toCommercialResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommercialResponsedto> findAllCommercials() {
        List<Commercial> commercials = commercialDao.findAll();
        return commercials.stream()
                .map(commercialMapper::toCommercialResponseDto)
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public CommercialResponsedto findById(int id) {
        Optional<Commercial> commercialOpt = commercialDao.findById(id);
        if (commercialOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("commercial.id.not.found"));
        }
        return commercialMapper.toCommercialResponseDto(commercialOpt.get());
    }

    @Override
    public CommercialResponsedto partiallyUpdateCommercial(int idCommercial, CommercialRequestDto commercialRequestDto) {
        Optional<Commercial> commercialOpt = commercialDao.findById(idCommercial);
        if (commercialOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("commercial.id.not.found"));
        }
        Commercial commercial = commercialOpt.get();
        if (commercialRequestDto.brand() != null && !commercialRequestDto.brand().isBlank()) {
            commercial.setBrand(commercialRequestDto.brand());
        }
        if (commercialRequestDto.model() != null && !commercialRequestDto.model().isBlank()) {
            commercial.setModel(commercialRequestDto.model());
        }
        if (commercialRequestDto.color() != null && !commercialRequestDto.color().isBlank()) {
            commercial.setColor(commercialRequestDto.color());
        }
        if (commercialRequestDto.nbPlaces() != null) {
            commercial.setNbPlaces(commercialRequestDto.nbPlaces());
        }
        if (commercialRequestDto.fuelType() != null){
            commercial.setFuelType(commercialRequestDto.fuelType());
        }
        if (commercialRequestDto.transmission() != null){
            commercial.setTransmission(commercialRequestDto.transmission());
        }
        if (commercialRequestDto.airConditioning() != null){
            commercial.setAirConditioning(commercialRequestDto.airConditioning());
        }
        if (commercialRequestDto.maximalLoad() != null) {
            commercial.setMaximalLoad(commercialRequestDto.maximalLoad());
        }
        if (commercialRequestDto.weight() != null) {
            commercial.setWeight(commercialRequestDto.weight());
        }
        if (commercialRequestDto.capacity() != null) {
            commercial.setCapacity(commercialRequestDto.capacity());
        }
        return commercialMapper.toCommercialResponseDto(commercial);
    }

    @Override
    public void deleteCommercial(int idCommercial) throws VehiculeException {
        Optional<Commercial>  commercialOpt = commercialDao.findById(idCommercial);
        if (commercialOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("commercial.id.not.found"));
        }
        commercialDao.deleteById(idCommercial);
    }


    private void verify(CommercialRequestDto commercialRequestDto) {
        if (commercialRequestDto == null) {
            throw new VehiculeException(messages.getMessage("commercial.null"));
        }
        if (commercialRequestDto.brand() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.brand.null"));
        }
        if (commercialRequestDto.model() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.model.null"));
        }
        if (commercialRequestDto.color() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.color.null"));
        }
        if (commercialRequestDto.nbPlaces() == null || commercialRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage("commercial.nbPlaces.null"));
        }
        if (commercialRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage("commercial.fuelType.null"));
        }
        if (commercialRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage("commercial.transmission.null"));
        }
        if (commercialRequestDto.airConditioning() ==  null) {
            throw new VehiculeException(messages.getMessage("commercial.airConditioning.null"));
        }
        if (commercialRequestDto.maximalLoad() == null || commercialRequestDto.maximalLoad() < 0 || commercialRequestDto.maximalLoad().isNaN()) {
            throw new VehiculeException(messages.getMessage("commercial.maximalLoad.null"));
        }
        if (commercialRequestDto.weight() == null || commercialRequestDto.weight() < 0 || commercialRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage("commercial.weight.null"));
        }
        if (commercialRequestDto.capacity() == null || commercialRequestDto.capacity() < 0 || commercialRequestDto.capacity().isNaN()) {
            throw new VehiculeException(messages.getMessage("commercial.capacity.null"));
        }
        if (commercialRequestDto.commercialTypes() == null) {
            throw new VehiculeException(messages.getMessage("commercial.motorcycleTypes.null"));
        }
    }
}
