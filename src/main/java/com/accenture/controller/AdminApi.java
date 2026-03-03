package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.AdminRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Admins", description = "Admin management API")
@RequestMapping("/admins")
public interface AdminApi {

    @Operation(summary = "Add a new admin")
    @ApiResponse(responseCode = "201", description = "Created admin")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addAdmin(@RequestBody AdminRequestDto adminRequestDto);
}
