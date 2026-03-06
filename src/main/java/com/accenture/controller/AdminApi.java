package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Admins", description = "Admin management API")
@RequestMapping("/admins")
public interface AdminApi {

    @Operation(summary = "Add a new admin")
    @ApiResponse(responseCode = "201", description = "Created admin")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addAdmin(@RequestBody AdminRequestDto adminRequestDto);

    @Operation(summary = "Get an admin by its id")
    @ApiResponse(responseCode = "200", description = "Admin found")
    @ApiResponse(responseCode = "404", description = "Admin not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}/{email}")
    ResponseEntity<AdminResponseDto> getAdmin(@Parameter(description = "ID of the admin", required = true) @PathVariable("id") int idAdmin, @PathVariable("email") String email);

    @Operation(summary = "Partially update an admin")
    @ApiResponse(responseCode = "200", description = "Admin partially updated")
    @ApiResponse(responseCode = "404", description = "Admin not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<AdminResponseDto> patchAdmin(@Parameter(description = "ID of the admin", required = true) @PathVariable("id") int idAdmin, String email, @RequestBody AdminRequestDto adminRequestDto);

    @Operation(summary = "Delete an admin by its id")
    @ApiResponse(responseCode = "204", description = "Admin deleted")
    @ApiResponse(responseCode = "404", description = "Admin not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAdmin(@Parameter(description = "ID of the admin", required = true) @PathVariable("id") int idAdmin, String email);
}
