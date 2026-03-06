package com.accenture.controller.impl;

import com.accenture.controller.AdminApi;
import com.accenture.service.AdminService;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<AdminResponseDto> getAdmin(int idAdmin, String email) {
        return ResponseEntity.ok(adminService.findById(idAdmin, email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<AdminResponseDto> patchAdmin(int idAdmin, String email, AdminRequestDto adminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.partiallyUpdateAdmin(idAdmin, email, adminRequestDto);
        return ResponseEntity.ok(adminResponseDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Void> deleteAdmin(int idAdmin, String email) {
        adminService.deleteAdmin(idAdmin, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
