package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.mapper.RecreationalMapper;
import com.accenture.model.Recreational;
import com.accenture.repository.RecreationalDao;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;
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

    private final RecreationalDao  recreationalDao;
    private final RecreationalMapper  recreationalMapper;
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
        Optional<Recreational> recreationalOpt = recreationalDao.findById(id);
        if (recreationalOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("recreational.id.not.found"));
        }
        return recreationalMapper.toRecreationalResponseDto(recreationalOpt.get());
    }

    @Override
    public RecreationalResponseDto partiallyUpdateRecreational(int idRecreational, RecreationalRequestDto recreationalRequestDto) {
        Optional<Recreational>  recreationalOpt = recreationalDao.findById(idRecreational);
        if (recreationalOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("recreational.id.not.found"));
        }
        Recreational recreational = recreationalOpt.get();
        if (recreationalRequestDto.vehiculeRequestDto().brand() != null && !recreationalRequestDto.vehiculeRequestDto().brand().isBlank()) {
            recreational.setBrand(recreationalRequestDto.vehiculeRequestDto().brand());
        }
        if (recreationalRequestDto.vehiculeRequestDto().model() != null && !recreationalRequestDto.vehiculeRequestDto().model().isBlank()) {
            recreational.setModel(recreationalRequestDto.vehiculeRequestDto().model());
        }
        if (recreationalRequestDto.vehiculeRequestDto().color() != null && !recreationalRequestDto.vehiculeRequestDto().color().isBlank()) {
            recreational.setColor(recreationalRequestDto.vehiculeRequestDto().color());
        }
        if (recreationalRequestDto.nbPlaces() != null){
            recreational.setNbPlace(recreationalRequestDto.nbPlaces());
        }
        if (recreationalRequestDto.fuelType() != null){
            recreational.setFuelType(recreationalRequestDto.fuelType());
        }
        if (recreationalRequestDto.transmission() != null){
            recreational.setTransmission(recreationalRequestDto.transmission());
        }
        if (recreationalRequestDto.airConditioning() != null){
            recreational.setAirConditioning(recreationalRequestDto.airConditioning());
        }
        if (recreationalRequestDto.weight() != null){
            recreational.setWeight(recreationalRequestDto.weight());
        }
        if (recreationalRequestDto.height() != null){
            recreational.setHeight(recreationalRequestDto.height());
        }
        if (recreationalRequestDto.nbBerths() != null){
            recreational.setNbBerths(recreationalRequestDto.nbBerths());
        }
        if (recreationalRequestDto.providedKitchenEquipment() != null){
            recreational.setProvidedKitchenEquipment(recreationalRequestDto.providedKitchenEquipment());
        }
        if (recreationalRequestDto.providedBedding() != null){
            recreational.setProvidedBedding(recreationalRequestDto.providedBedding());
        }
        if (recreationalRequestDto.refregiratorEquipment() != null){
            recreational.setRefregiratorEquipment(recreationalRequestDto.refregiratorEquipment());
        }
        if (recreationalRequestDto.showerEquipment() != null){
            recreational.setShowerEquipment(recreationalRequestDto.showerEquipment());
        }
        if (recreationalRequestDto.recreationalTypes() != null){
            recreational.setRecreationalTypes(recreationalRequestDto.recreationalTypes());
        }
        return recreationalMapper.toRecreationalResponseDto(recreational);

    }

    @Override
    public void deleteRecreational(int idRecreational) throws VehiculeException {
        Optional<Recreational> recreationalOpt = recreationalDao.findById(idRecreational);
        if (recreationalOpt.isEmpty()) {
            throw new VehiculeException(messages.getMessage("recreational.id.not.found"));
        }
        recreationalDao.deleteById(idRecreational);
    }

    private void verify(RecreationalRequestDto recreationalRequestDto) {
        if (recreationalRequestDto == null) {
            throw new VehiculeException(messages.getMessage("recreational.null"));
        }
        if (recreationalRequestDto.vehiculeRequestDto().brand() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.brand.null"));
        }
        if (recreationalRequestDto.vehiculeRequestDto().model() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.model.null"));
        }
        if (recreationalRequestDto.vehiculeRequestDto().color() ==  null) {
            throw new VehiculeException(messages.getMessage("vehicule.color.null"));
        }
        if (recreationalRequestDto.nbPlaces() == null || recreationalRequestDto.nbPlaces() < 0) {
            throw new VehiculeException(messages.getMessage("recreational.nbPlaces.null"));
        }
        if (recreationalRequestDto.fuelType() == null) {
            throw new VehiculeException(messages.getMessage("recreational.fuelType.null"));
        }
        if (recreationalRequestDto.transmission() == null) {
            throw new VehiculeException(messages.getMessage("recreational.transmission.null"));
        }
        if (recreationalRequestDto.airConditioning() ==  null) {
            throw new VehiculeException(messages.getMessage("recreational.airConditioning.null"));
        }
        if (recreationalRequestDto.weight() == null || recreationalRequestDto.weight() < 0 || recreationalRequestDto.weight().isNaN()) {
            throw new VehiculeException(messages.getMessage("recreational.weight.null"));
        }
        if (recreationalRequestDto.height() == null || recreationalRequestDto.height() < 0 || recreationalRequestDto.height().isNaN()) {
            throw new VehiculeException(messages.getMessage("recreational.height.null"));
        }
        if (recreationalRequestDto.nbBerths() == null || recreationalRequestDto.nbBerths() < 0) {
            throw new VehiculeException(messages.getMessage("recreational.nbBerths.null"));
        }
        if (recreationalRequestDto.providedKitchenEquipment() ==  null) {
            throw new VehiculeException(messages.getMessage("recreational.provided.kitchen.equipment.null"));
        }
        if (recreationalRequestDto.providedBedding() ==  null) {
            throw new VehiculeException(messages.getMessage("recreational.provided.bedding.null"));
        }
        if (recreationalRequestDto.refregiratorEquipment() ==  null) {
            throw new VehiculeException(messages.getMessage("recreational.refregirator.equipment.null"));
        }
        if (recreationalRequestDto.showerEquipment() ==  null) {
            throw new VehiculeException(messages.getMessage("recreational.shower.equipment.null"));
        }
        if (recreationalRequestDto.recreationalTypes() == null) {
            throw new VehiculeException(messages.getMessage("recreational.recreationalTypes.null"));
        }
    }
}
