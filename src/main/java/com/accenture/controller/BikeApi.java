package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.BikeRequestDto;
import com.accenture.service.dto.BikeResponseDto;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bikes", description = "Bike management API")
@RequestMapping("/bikes")
public interface BikeApi {

    @Operation(summary = "Add a new bike")
    @ApiResponse(responseCode = "201", description = "Created bike")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addBike(@RequestBody BikeRequestDto bikeRequestDto);

    @Operation(summary = "List of all bikes")
    @ApiResponse(responseCode = "200", description = "List of bikes")
    @GetMapping
    ResponseEntity<List<BikeResponseDto>> bikes();

    @Operation(summary = "Get a bike by its id")
    @ApiResponse(responseCode = "200", description = "Bike found")
    @ApiResponse(responseCode = "404", description = "Bike not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<BikeResponseDto> bike(@Parameter(description = "ID of the bike", required = true) @PathVariable("id") int idBike);

    @Operation(summary = "Partially update a bike")
    @ApiResponse(responseCode = "200", description = "Bike partially updated")
    @ApiResponse(responseCode = "404", description = "Bike not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<BikeResponseDto> patchBike(@Parameter(description = "ID of the bike", required = true) @PathVariable("id") int idBike, @RequestBody BikeRequestDto bikeRequestDto);

    @Operation(summary = "Delete a bike by its id")
    @ApiResponse(responseCode = "204", description = "Bike deleted")
    @ApiResponse(responseCode = "404", description = "Bike not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBike(@Parameter(description = "ID of the bike", required = true) @PathVariable("id") int idBike);
}
