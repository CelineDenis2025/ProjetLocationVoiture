package com.accenture.controller.impl;

import com.accenture.controller.AdminApi;
import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Override
    public ResponseEntity<AdminResponseDto> admin(int idAdmin, String email, String password) {
        return ResponseEntity.ok(adminService.findById(idAdmin, email, password));
    }

    @Override
    public ResponseEntity<AdminResponseDto> patchAdmin(int idAdmin, String email, String password, AdminRequestDto adminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.partiallyUpdateAdmin(idAdmin, email, password, adminRequestDto);
        return ResponseEntity.ok(adminResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteAdmin(int idAdmin, String email, String password) {
        adminService.deleteAdmin(idAdmin, email, password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
