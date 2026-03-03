package com.accenture.controller.impl;

import com.accenture.controller.AdminApi;
import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<Void> addAdmin(AdminRequestDto adminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.addAdmin(adminRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adminResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
