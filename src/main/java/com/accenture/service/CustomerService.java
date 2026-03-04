package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws ConnectedUserException;
    CustomerResponseDto findById(int id, String email, String password);
    CustomerResponseDto partiallyUpdateCustomer(int idCustomer, String email, String password, CustomerRequestDto customerRequestDto);
    void deleteCustomer(int idCustomer, String email, String password) throws ConnectedUserException;


}
