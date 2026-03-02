package com.accenture.controller.impl;

import com.accenture.controller.CommercialApi;
import com.accenture.service.CommercialService;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommercialController implements CommercialApi {

    private final CommercialService commercialService;

    @Override
    public ResponseEntity<Void> addCommercial(CommercialRequestDto commercialRequestDto) {
        CommercialResponsedto commercialResponseDto = commercialService.addCommercial(commercialRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commercialResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<List<CommercialResponsedto>> commercials() {
        return ResponseEntity.ok(commercialService.findAllCommercials());
    }

    @Override
    public ResponseEntity<CommercialResponsedto> commercial(int idCommercial) {
        return ResponseEntity.ok(commercialService.findById(idCommercial));
    }

    @Override
    public ResponseEntity<CommercialResponsedto> patchCommercial(int idCommercial, CommercialRequestDto commercialRequestDto) {
        CommercialResponsedto commercialResponsedto = commercialService.partiallyUpdateCommercial(idCommercial, commercialRequestDto);
        return ResponseEntity.ok(commercialResponsedto);
    }

    @Override
    public ResponseEntity<Void> deleteCommercial(int idCommercial) {
        commercialService.deleteCommercial(idCommercial);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
