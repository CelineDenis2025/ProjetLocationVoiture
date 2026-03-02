package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Commercials", description = "Commercial management API")
@RequestMapping("/commercials")
public interface CommercialApi {

    @Operation(summary = "Add a new commercial")
    @ApiResponse(responseCode = "201", description = "Created commercial")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addCommercial(@RequestBody CommercialRequestDto commercialRequestDto);

    @Operation(summary = "List of all commercials")
    @ApiResponse(responseCode = "200", description = "List of commercials")
    @GetMapping
    ResponseEntity<List<CommercialResponsedto>> commercials();

    @Operation(summary = "Get a commercial by its id")
    @ApiResponse(responseCode = "200", description = "Commercial found")
    @ApiResponse(responseCode = "404", description = "Commercial not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<CommercialResponsedto> commercial(@Parameter(description = "ID of the commercial", required = true) @PathVariable("id") int idCommercial);

    @Operation(summary = "Partially update a commercial")
    @ApiResponse(responseCode = "200", description = "commercial partially updated")
    @ApiResponse(responseCode = "404", description = "commercial not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<CommercialResponsedto> patchCommercial(@Parameter(description = "ID of the commercial", required = true) @PathVariable("id") int idCommercial, @RequestBody CommercialRequestDto commercialRequestDto);

    @Operation(summary = "Delete a commercial by its id")
    @ApiResponse(responseCode = "204", description = "Commercial deleted")
    @ApiResponse(responseCode = "404", description = "Commercial not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCommercial(@Parameter(description = "ID of the commercial", required = true) @PathVariable("id") int idCommercial);
}
