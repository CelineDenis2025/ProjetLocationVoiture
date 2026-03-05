package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.RecreationalMapper;
import com.accenture.model.Recreational;
import com.accenture.repository.RecreationalDao;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;
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
public class RecreationalServiceImpl implements RecreationalService {

    private final RecreationalDao recreationalDao;
    private final RecreationalMapper recreationalMapper;
    private final MessageSourceAccessor messages;


    @Override
    public RecreationalResponseDto addRecreational(RecreationalRequestDto recreationalRequestDto) throws VehiculeException {
        verify(recreationalRequestDto);
        Recreational recreational = recreationalMapper.toRecreational(recreationalRequestDto);
        Recreational saved = recreationalDao.save(recreational);
        return recreationalMapper.toRecreationalResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecreationalResponseDto> findAllRecreationals() {
        List<Recreational> recreationals = recreationalDao.findAll();
        return recreationals.stream()
                .map(recreationalMapper::toRecreationalResponseDto)
                .toList();
    }

    @Override
    public RecreationalResponseDto findById(int id) {
        Recreational recreational = validateRecreational(id);
        return recreationalMapper.toRecreationalResponseDto(recreational);
    }

    @Override
    public RecreationalResponseDto partiallyUpdateRecreational(int idRecreational, RecreationalRequestDto recreationalRequestDto) {
        Recreational recreational = validateRecreational(idRecreational);

        if (recreationalRequestDto.vehiculeRequestDto().brand() != null && !recreationalRequestDto.vehiculeRequestDto().brand().isBlank()) {
            recreational.setBrand(recreationalRequestDto.vehiculeRequestDto().brand());
        }
        if (recreationalRequestDto.vehiculeRequestDto().model() != null && !recreationalRequestDto.vehiculeRequestDto().model().isBlank()) {
            recreational.setModel(recreationalRequestDto.vehiculeRequestDto().model());
        }
        if (recreationalRequestDto.vehiculeRequestDto().color() != null && !recreationalRequestDto.vehiculeRequestDto().color().isBlank()) {
            recreational.setColor(recreationalRequestDto.vehiculeRequestDto().color());
        }
        if (recreationalRequestDto.nbPlaces() != null) {
            recreational.setNbPlace(recreationalRequestDto.nbPlaces());
        }
        if (recreationalRequestDto.fuelType() != null) {
            recreational.setFuelType(recreationalRequestDto.fuelType());
        }
        if (recreationalRequestDto.transmission() != null) {
            recreational.setTransmission(recreationalRequestDto.transmission());
        }
        if (recreationalRequestDto.airConditioning() != null) {
            recreational.setAirConditioning(recreationalRequestDto.airConditioning());
        }
        if (recreationalRequestDto.weight() != null) {
            recreational.setWeight(recreationalRequestDto.weight());
        }
        if (recreationalRequestDto.height() != null) {
            recreational.setHeight(recreationalRequestDto.height());
        }
        if (recreationalRequestDto.nbBerths() != null) {
            recreational.setNbBerths(recreationalRequestDto.nbBerths());
        }
        if (recreationalRequestDto.providedKitchenEquipment() != null) {
            recreational.setProvidedKitchenEquipment(recreationalRequestDto.providedKitchenEquipment());
        }
        if (recreationalRequestDto.providedBedding() != null) {
            recreational.setProvidedBedding(recreationalRequestDto.providedBedding());
        }
        if (recreationalRequestDto.refregiratorEquipment() != null) {
            recreational.setRefregiratorEquipment(recreationalRequestDto.refregiratorEquipment());
        }
        if (recreationalRequestDto.showerEquipment() != null) {
            recreational.setShowerEquipment(recreationalRequestDto.showerEquipment());
        }
        if (recreationalRequestDto.recreationalTypes() != null) {
            recreational.setRecreationalTypes(recreationalRequestDto.recreationalTypes());
        }
        return recreationalMapper.toRecreationalResponseDto(recreational);

    }

    @Override
    public void deleteRecreational(int idRecreational) throws VehiculeException {
        validateRecreational(idRecreational);
        recreationalDao.deleteById(idRecreational);
    }

    private void verify(RecreationalRequestDto recreationalRequestDto) {
        if (recreationalRequestDto == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_NULL));
        }
        if (recreationalRequestDto.vehiculeRequestDto().brand() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_BRAND_NULL));
        }
        if (recreationalRequestDto.vehiculeRequestDto().model() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_MODEL_NULL));
        }
        if (recreationalRequestDto.vehiculeRequestDto().color() == null) {
            throw new VehiculeException(messages.getMessage(Messages.VEHICULE_COLOR_NULL));
        }
        if (recreationalRequestDto.nbPlaces() == null || recreationalRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_NB_PLACES_NULL));
        }
        if (recreationalRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_FUEL_TYPE_NULL));
        }
        if (recreationalRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_TRANSMISSION_NULL));
        }
        if (recreationalRequestDto.airConditioning() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_AIR_CONDITIONING_NULL));
        }
        if (recreationalRequestDto.weight() == null || recreationalRequestDto.weight() < 0 || recreationalRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_WEIGHT_NULL));
        }
        if (recreationalRequestDto.height() == null || recreationalRequestDto.height() < 0 || recreationalRequestDto.height().isNaN()) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_HEIGHT_NULL));
        }
        if (recreationalRequestDto.nbBerths() == null || recreationalRequestDto.nbBerths() < 0) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_NB_BERTHS_NULL));
        }
        if (recreationalRequestDto.providedKitchenEquipment() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_PROVIDED_KITCHEN_EQUIPMENT_NULL));
        }
        if (recreationalRequestDto.providedBedding() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_PROVIDED_BEDDING_NULL));
        }
        if (recreationalRequestDto.refregiratorEquipment() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_REFREGIRATOR_EQUIPMENT_NULL));
        }
        if (recreationalRequestDto.showerEquipment() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_SHOWER_EQUIPMENT_NULL));
        }
        if (recreationalRequestDto.recreationalTypes() == null) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_TYPE_NULL));
        }
    }

    private Recreational validateRecreational(int idRecreational) {
        Optional<Recreational> recreationalOpt = recreationalDao.findById(idRecreational);
        if (recreationalOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage(Messages.RECREATIONAL_ID_NOT_FOUND));
        }
        return recreationalOpt.get();
    }
}
