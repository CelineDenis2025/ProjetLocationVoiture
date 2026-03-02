package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Motorcycles", description = "Motorcycle management API")
@RequestMapping("/motorcycles")
public interface MotorcycleApi {

    @Operation(summary = "Add a new motorcycle")
    @ApiResponse(responseCode = "201", description = "Created motorcycle")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addMotorcycle(@RequestBody MotorcycleRequestDto motorcycleRequestDto);

    @Operation(summary = "List of all motorcycles")
    @ApiResponse(responseCode = "200", description = "List of motorcycles")
    @GetMapping
    ResponseEntity<List<MotorcycleResponseDto>> motorcycles();

    @Operation(summary = "Get a motorcycle by its id")
    @ApiResponse(responseCode = "200", description = "motorcycle found")
    @ApiResponse(responseCode = "404", description = "motorcycle not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<MotorcycleResponseDto> motorcycle(@Parameter(description = "ID of the motorcycle", required = true) @PathVariable("id") int idMotorcycle);

    @Operation(summary = "Partially update a motorcycle")
    @ApiResponse(responseCode = "200", description = "motorcycle partially updated")
    @ApiResponse(responseCode = "404", description = "motorcycle not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<MotorcycleResponseDto> patchMotorcycle(@Parameter(description = "ID of the motorcycle", required = true) @PathVariable("id") int idMotorcycle, @RequestBody MotorcycleRequestDto motorcycleRequestDto);

    @Operation(summary = "Delete a motorcycle by its id")
    @ApiResponse(responseCode = "204", description = "motorcycle deleted")
    @ApiResponse(responseCode = "404", description = "motorcycle not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletedeleteMotorcycleCar(@Parameter(description = "ID of the motorcycle", required = true) @PathVariable("id") int idMotorcycle);
}
