package com.accenture.controller.impl;

import com.accenture.controller.BikeApi;
import com.accenture.service.BikeService;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class BikeController implements BikeApi {

    private final BikeService bikeService;


    @Override
    public ResponseEntity<Void> addBike(BikeRequestDto bikeRequestDto) {
        BikeResponseDto bikeResponseDto = bikeService.addBike(bikeRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bikeResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<BikeResponseDto>> bikes() {
        return ResponseEntity.ok(bikeService.findAllBikes());
    }

    @Override
    public ResponseEntity<BikeResponseDto> bike(int idBike) {
        return ResponseEntity.ok(bikeService.findById(idBike));
    }

    @Override
    public ResponseEntity<BikeResponseDto> patchBike(int idBike, BikeRequestDto bikeRequestDto) {
        BikeResponseDto bikeResponseDto = bikeService.partiallyUpdateBike(idBike, bikeRequestDto);
        return ResponseEntity.ok(bikeResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteBike(int idBike) {
        bikeService.deleteBike(idBike);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
