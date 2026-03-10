package com.accenture.controller.impl;

import com.accenture.controller.RecreationalApi;
import com.accenture.service.RecreationalService;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;
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
public class RecreationalController implements RecreationalApi {

    private RecreationalService recreationalService;


    @Override
    public ResponseEntity<Void> addRecreational(@Valid RecreationalRequestDto recreationalRequestDto) {
        RecreationalResponseDto recreationalResponseDto = recreationalService.addRecreational(recreationalRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recreationalResponseDto.id())
                .toUri();
        return  ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<RecreationalResponseDto>> recreationals() {
        return ResponseEntity.ok(recreationalService.findAllRecreationals());
    }

    @Override
    public ResponseEntity<RecreationalResponseDto> recreational(int idRecreational) {
        return ResponseEntity.ok(recreationalService.findById(idRecreational));
    }

    @Override
    public ResponseEntity<RecreationalResponseDto> patchRecreational(int idRecreational, @Valid RecreationalRequestDto recreationalRequestDto) {
        RecreationalResponseDto recreationalResponseDto = recreationalService.partiallyUpdateRecreational(idRecreational, recreationalRequestDto);
        return  ResponseEntity.ok(recreationalResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteRecreational(int idRecreational) {
        recreationalService.deleteRecreational(idRecreational);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
