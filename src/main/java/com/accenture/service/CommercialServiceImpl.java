package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.CommercialMapper;
import com.accenture.model.Car;
import com.accenture.model.Commercial;
import com.accenture.repository.CommercialDao;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
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
public class CommercialServiceImpl implements CommercialService {

    private final CommercialDao commercialDao;
    private final CommercialMapper commercialMapper;
    private final MessageSourceAccessor messages;


    @Override
    public CommercialResponsedto addCommercial(CommercialRequestDto commercialRequestDto) throws VehiculeException {
        verify(commercialRequestDto);
        Commercial commercial = commercialMapper.toCommercial(commercialRequestDto);
        Commercial saved = commercialDao.save(commercial);
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
        Commercial commercial = validateCommercial(id);
        return commercialMapper.toCommercialResponseDto(commercial);
    }

    @Override
    public CommercialResponsedto partiallyUpdateCommercial(int idCommercial, CommercialRequestDto commercialRequestDto) {
        Commercial commercial = validateCommercial(idCommercial);

        if (commercialRequestDto.vehiculeRequestDto().brand() != null && !commercialRequestDto.vehiculeRequestDto().brand().isBlank()) {
            commercial.setBrand(commercialRequestDto.vehiculeRequestDto().brand());
        }
        if (commercialRequestDto.vehiculeRequestDto().model() != null && !commercialRequestDto.vehiculeRequestDto().model().isBlank()) {
            commercial.setModel(commercialRequestDto.vehiculeRequestDto().model());
        }
        if (commercialRequestDto.vehiculeRequestDto().color() != null && !commercialRequestDto.vehiculeRequestDto().color().isBlank()) {
            commercial.setColor(commercialRequestDto.vehiculeRequestDto().color());
        }
        if (commercialRequestDto.nbPlaces() != null) {
            commercial.setNbPlaces(commercialRequestDto.nbPlaces());
        }
        if (commercialRequestDto.fuelType() != null) {
            commercial.setFuelType(commercialRequestDto.fuelType());
        }
        if (commercialRequestDto.transmission() != null) {
            commercial.setTransmission(commercialRequestDto.transmission());
        }
        if (commercialRequestDto.airConditioning() != null) {
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
        validateCommercial(idCommercial);
        commercialDao.deleteById(idCommercial);
    }


    private void verify(CommercialRequestDto commercialRequestDto) {
        if (commercialRequestDto == null) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_NULL));
        }
        if (commercialRequestDto.vehiculeRequestDto().brand() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_BRAND_NULL));
        }
        if (commercialRequestDto.vehiculeRequestDto().model() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_MODEL_NULL));
        }
        if (commercialRequestDto.vehiculeRequestDto().color() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_COLOR_NULL));
        }
        if (commercialRequestDto.nbPlaces() == null || commercialRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_NB_PLACES_NULL));
        }
        if (commercialRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_FUEL_TYPE_NULL));
        }
        if (commercialRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_TRANSMMISSION_NULL));
        }
        if (commercialRequestDto.airConditioning() == null) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_AIR_CONDITIONING_NULL));
        }
        if (commercialRequestDto.maximalLoad() == null || commercialRequestDto.maximalLoad() < 0 || commercialRequestDto.maximalLoad().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_MAXIMAL_LOAD_NULL));
        }
        if (commercialRequestDto.weight() == null || commercialRequestDto.weight() < 0 || commercialRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_WEIGHT_NULL));
        }
        if (commercialRequestDto.capacity() == null || commercialRequestDto.capacity() < 0 || commercialRequestDto.capacity().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_CAPACITY_NULL));
        }
        if (commercialRequestDto.commercialTypes() == null) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_TYPE_NULL));
        }
    }


    private Commercial validateCommercial(int idCommercial) {
        Optional<Commercial> commercialOpt = commercialDao.findById(idCommercial);
        if (commercialOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage(Messages.COMMERCIAL_ID_NOT_FOUND));
        }
        return commercialOpt.get();
    }
}
