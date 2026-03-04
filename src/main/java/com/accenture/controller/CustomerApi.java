package com.accenture.controller;

import com.accenture.controller.advice.ErrorDto;
import com.accenture.service.dto.CommercialRequestDto;
import com.accenture.service.dto.CommercialResponsedto;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customers", description = "Customer management API")
@RequestMapping("/customers")
public interface CustomerApi {

    @Operation(summary = "Add a new customer")
    @ApiResponse(responseCode = "201", description = "Created customer")
    @ApiResponse(responseCode = "400", description = "Invalid request",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PostMapping
    ResponseEntity<Void> addCustomer(@RequestBody CustomerRequestDto customerRequestDto);

    @Operation(summary = "Get a customer by its id")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponseDto> customer(@Parameter(description = "ID of the customer", required = true) @PathVariable("id") int idCustomer, String email,  String password);

    @Operation(summary = "Partially update a customer")
    @ApiResponse(responseCode = "200", description = "Customer partially updated")
    @ApiResponse(responseCode = "404", description = "Customer not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @PatchMapping("/{id}")
    ResponseEntity<CustomerResponseDto> patchCustomer(@Parameter(description = "ID of the customer", required = true) @PathVariable("id") int idCustomer, String email,  String password, @RequestBody CustomerRequestDto customerRequestDto);

    @Operation(summary = "Delete a customer by its id")
    @ApiResponse(responseCode = "204", description = "Customer deleted")
    @ApiResponse(responseCode = "404", description = "Customer not found",
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomer(@Parameter(description = "ID of the customer", required = true) @PathVariable("id") int idCustomer, String email,  String password);
}
