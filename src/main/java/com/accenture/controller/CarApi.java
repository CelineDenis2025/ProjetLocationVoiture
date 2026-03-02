package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
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

@Tag(name = "Cars", description = "Car management API")
@RequestMapping("/cars")
public interface CarApi {

    @Operation(summary = "Add a new car")
    @ApiResponse(responseCode = "201", description = "Created car")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addCar(@RequestBody CarRequestDto carRequestDto);

    @Operation(summary = "List of all cars")
    @ApiResponse(responseCode = "200", description = "List of cars")
    @GetMapping
    ResponseEntity<List<CarResponseDto>> cars();

    @Operation(summary = "Get a car by its id")
    @ApiResponse(responseCode = "200", description = "Car found")
    @ApiResponse(responseCode = "404", description = "Car not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<CarResponseDto> car(@Parameter(description = "ID of the car", required = true) @PathVariable("id") int idCar);

    @Operation(summary = "Partially update a car")
    @ApiResponse(responseCode = "200", description = "Car partially updated")
    @ApiResponse(responseCode = "404", description = "Car not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<CarResponseDto> patchCar(@Parameter(description = "ID of the car", required = true) @PathVariable("id") int idCar, @RequestBody CarRequestDto requestDto);

    @Operation(summary = "Delete a car by its id")
    @ApiResponse(responseCode = "204", description = "Car deleted")
    @ApiResponse(responseCode = "404", description = "Car not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCar(@Parameter(description = "ID of the car", required = true) @PathVariable("id") int idCar);
}
