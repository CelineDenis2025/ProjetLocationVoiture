package com.accenture.controller.impl;

import com.accenture.controller.CustomerApi;
import com.accenture.service.CustomerService;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<Void> addCustomer(CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<CustomerResponseDto> customer(int idCustomer) {
        return ResponseEntity.ok(customerService.findById(idCustomer));
    }

}
