package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.CustomerMapper;
import com.accenture.model.Customer;
import com.accenture.model.enums.Role;
import com.accenture.repository.CustomerDao;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper customerMapper;
    private final MessageSourceAccessor messages;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws ConnectedUserException {
        verify(customerRequestDto);
        Customer customer = customerMapper.toCustomer(customerRequestDto);
//        customer.setPassword(passwordEncoder.encode(customerRequestDto.password()));
        Customer saved = customerDao.save(customer);
        return customerMapper.toCustomerResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponseDto findById(int id) {
        Optional<Customer> customerOpt = customerDao.findById(id);
        if (customerOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage("customer.id.not.found"));
        }
        if (customerOpt.get().getRole() == Role.USER) {

        }
        return customerMapper.toCustomerResponseDto(customerOpt.get());
    }


    private void verify(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null) {
            throw new ConnectedUserException(messages.getMessage("customer.null"));
        }
        if (customerRequestDto.firstName() == null || customerRequestDto.firstName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.firstname.null"));
        }
        if (customerRequestDto.lastName() == null || customerRequestDto.lastName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.lastname.null"));
        }
        if (customerRequestDto.street() == null || customerRequestDto.street().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("customer.street.null"));
        }
        if (customerRequestDto.postalCode() == null || customerRequestDto.postalCode().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("customer.postalCode.null"));
        }
        if (customerRequestDto.city() == null || customerRequestDto.city().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("customer.city.null"));
        }
        if (customerRequestDto.email() == null || customerRequestDto.email().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.null"));
        }
        if (!customerRequestDto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.invalid"));
        }
        if (customerDao.existsByEmail(customerRequestDto.email())) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.already.exists"));
        }
        if (customerRequestDto.dateOfBirth() == null) {
            throw new ConnectedUserException(messages.getMessage("customer.dateOfBirth.null"));
        }
        if (!customerRequestDto.dateOfBirth().isBefore(LocalDate.now())) {
            throw new ConnectedUserException(messages.getMessage("customer.dateOfBirth.past"));
        }
//        if (customerRequestDto.dateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
//            throw new ConnectedUserException(messages.getMessage("customer.age.too.young"));
//        }
//        if (customerRequestDto.dateOfBirth().isAfter(LocalDate.now().minusYears(120))) {
//            throw new ConnectedUserException(messages.getMessage("customer.age.too.old"));
//        }
        if (customerRequestDto.password() == null || customerRequestDto.password().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.null"));
        }
        if (!customerRequestDto.password().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$")) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.invalid"));
        }
        if (customerRequestDto.licenses() == null) {
            throw new ConnectedUserException(messages.getMessage("customer.licenses.null"));
        }
        if (customerRequestDto.licenses().isEmpty()) {
            throw new ConnectedUserException(messages.getMessage("customer.licenses.empty"));
        }
//        if (customerRequestDto.role() == null) {
//            throw new ConnectedUserException(messages.getMessage("customer.role.null"));
//        }
    }
}