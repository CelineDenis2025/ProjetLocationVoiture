package com.accenture.controller.impl;

import com.accenture.controller.MotorcycleApi;
import com.accenture.service.MotorcycleService;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class MotorcycleController implements MotorcycleApi {

    private final MotorcycleService motorcycleService;

    @Override
    public ResponseEntity<Void> addMotorcycle(@Valid MotorcycleRequestDto motorcycleRequestDto) {
        MotorcycleResponseDto motorcycleResponseDto = motorcycleService.addMotorcycle(motorcycleRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(motorcycleResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<MotorcycleResponseDto>> motorcycles() {
        return ResponseEntity.ok(motorcycleService.findAllMotorcycles());
    }

    @Override
    public ResponseEntity<MotorcycleResponseDto> motorcycle(int idMotorcycle) {
        return ResponseEntity.ok(motorcycleService.findById(idMotorcycle));
    }

    @Override
    public ResponseEntity<MotorcycleResponseDto> patchMotorcycle(int idMotorcycle, @Valid MotorcycleRequestDto motorcycleRequestDto) {
        MotorcycleResponseDto motorcycleResponseDto = motorcycleService.partiallyUpdateMotorcycle(idMotorcycle, motorcycleRequestDto);
        return ResponseEntity.ok(motorcycleResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteMotorcycle(int idMotorcycle) {
        motorcycleService.deleteMotorcycle(idMotorcycle);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
