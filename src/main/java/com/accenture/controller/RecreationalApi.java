package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.MotorcycleRequestDto;
import com.accenture.service.dto.MotorcycleResponseDto;
import com.accenture.service.dto.RecreationalRequestDto;
import com.accenture.service.dto.RecreationalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Recreationals", description = "Recreational management API")
@RequestMapping("/recreationals")
public interface RecreationalApi {

    @Operation(summary = "Add a new recreational")
    @ApiResponse(responseCode = "201", description = "Created recreational")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addRecreational(@RequestBody RecreationalRequestDto recreationalRequestDto);

    @Operation(summary = "List of all recreationals")
    @ApiResponse(responseCode = "200", description = "List of recreationals")
    @GetMapping
    ResponseEntity<List<RecreationalResponseDto>> recreationals();

    @Operation(summary = "Get a recreational by its id")
    @ApiResponse(responseCode = "200", description = "Recreational found")
    @ApiResponse(responseCode = "404", description = "Recreational not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<RecreationalResponseDto> recreational(@Parameter(description = "ID of the recreational", required = true) @PathVariable("id") int idRecreational);

    @Operation(summary = "Partially update a recreational")
    @ApiResponse(responseCode = "200", description = "Recreational partially updated")
    @ApiResponse(responseCode = "404", description = "Recreational not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<RecreationalResponseDto> patchRecreational(@Parameter(description = "ID of the recreational", required = true) @PathVariable("id") int idRecreational, @RequestBody RecreationalRequestDto recreationalRequestDto);

    @Operation(summary = "Delete a recreational by its id")
    @ApiResponse(responseCode = "204", description = "Recreational deleted")
    @ApiResponse(responseCode = "404", description = "Recreational not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRecreational(@Parameter(description = "ID of the recreational", required = true) @PathVariable("id") int idRecreational);
}


