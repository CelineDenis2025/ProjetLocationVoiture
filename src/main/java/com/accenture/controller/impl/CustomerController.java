package com.accenture.controller.impl;

import com.accenture.controller.CustomerApi;
import com.accenture.service.CustomerService;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<Void> addCustomer(@Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public ResponseEntity<CustomerResponseDto> getCustomer(int idCustomer) {
        return ResponseEntity.ok(customerService.findById(idCustomer));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public ResponseEntity<CustomerResponseDto> patchCustomer(int idCustomer, @Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.partiallyUpdateCustomer(idCustomer, customerRequestDto);
        return  ResponseEntity.ok(customerResponseDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public ResponseEntity<Void> deleteCustomer(int idCustomer) {
        customerService.deleteCustomer(idCustomer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
