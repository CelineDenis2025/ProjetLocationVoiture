package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.CustomerMapper;
import com.accenture.model.Address;
import com.accenture.model.Customer;
import com.accenture.model.enums.Role;
import com.accenture.repository.ConnectedUserDao;
import com.accenture.repository.CustomerDao;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import com.accenture.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final ConnectedUserDao connectedUserDao;
    private final CustomerMapper customerMapper;
    private final MessageSourceAccessor messages;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws ConnectedUserException {
        verify(customerRequestDto);
        Customer customer = customerMapper.toCustomer(customerRequestDto);
        customer.setPassword(passwordEncoder.encode(customerRequestDto.connectedUserRequestDto().password()));
        Customer saved = customerDao.save(customer);
        return customerMapper.toCustomerResponseDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponseDto findById(int id) {
        Customer customer = validateCustomer(id);
        return customerMapper.toCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto partiallyUpdateCustomer(int idCustomer, CustomerRequestDto customerRequestDto) {
        Customer customer = validateCustomer(idCustomer);
        if (customerRequestDto.connectedUserRequestDto().firstName() != null && !customerRequestDto.connectedUserRequestDto().firstName().isBlank()){
            customer.setFirstName(customerRequestDto.connectedUserRequestDto().firstName());
        }
        if (customerRequestDto.connectedUserRequestDto().lastName() != null && !customerRequestDto.connectedUserRequestDto().lastName().isBlank()){
            customer.setLastName(customerRequestDto.connectedUserRequestDto().lastName());
        }
        if (customerRequestDto.connectedUserRequestDto().email() != null && !customerRequestDto.connectedUserRequestDto().email().isBlank()){
            customer.setEmail(customerRequestDto.connectedUserRequestDto().email());
        }
        if (customerRequestDto.connectedUserRequestDto().password() != null && !customerRequestDto.connectedUserRequestDto().password().isBlank()){
            customer.setPassword(customerRequestDto.connectedUserRequestDto().password());
        }
        if (customerRequestDto.dateOfBirth() != null){
            customer.setDateOfBirth(customerRequestDto.dateOfBirth());
        }
        if (customerRequestDto.licenses() != null){
            customer.setLicenses(customerRequestDto.licenses());
        }
        if (customerRequestDto.street() != null || customerRequestDto.postalCode() != null || customerRequestDto.city() != null) {
            Address address = customer.getAddress();
            if (address == null) {
                address = new Address();
                customer.setAddress(address);
            }
            if (customerRequestDto.street() != null && !customerRequestDto.street().isBlank()){
                address.setStreet(customerRequestDto.street());
            }
            if (customerRequestDto.postalCode() != null && !customerRequestDto.postalCode().isBlank()){
                address.setPostalCode(customerRequestDto.postalCode());
            }
            if (customerRequestDto.city() != null && !customerRequestDto.city().isBlank()){
                address.setCity(customerRequestDto.city());
            }
        }
        return customerMapper.toCustomerResponseDto(customer);
    }


    @Override
    public void deleteCustomer(int idCustomer) throws ConnectedUserException {
        validateCustomer(idCustomer);
        customerDao.deleteById(idCustomer);
    }


    private void verify(CustomerRequestDto customerRequestDto) {
        if (customerRequestDto == null) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_NULL));
        }
        if (customerRequestDto.connectedUserRequestDto().firstName() == null || customerRequestDto.connectedUserRequestDto().firstName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_FIRSTNAME_NULL));
        }
        if (customerRequestDto.connectedUserRequestDto().lastName() == null || customerRequestDto.connectedUserRequestDto().lastName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_LASTNAME_NULL));
        }
        if (customerRequestDto.street() == null || customerRequestDto.street().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_STREET_NULL));
        }
        if (customerRequestDto.postalCode() == null || customerRequestDto.postalCode().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_POSTAL_CODE_NULL));
        }
        if (customerRequestDto.city() == null || customerRequestDto.city().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_CITY_NULL));
        }
        if (customerRequestDto.connectedUserRequestDto().email() == null || customerRequestDto.connectedUserRequestDto().email().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_NULL));
        }
        if (!customerRequestDto.connectedUserRequestDto().email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_INVALID));
        }
        if (customerDao.existsByEmail(customerRequestDto.connectedUserRequestDto().email())) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_ALREADY_EXIST));
        }
        if (customerRequestDto.dateOfBirth() == null) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_DATE_OF_BIRTH_NULL));
        }
        if (!customerRequestDto.dateOfBirth().isBefore(LocalDate.now())) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_DATE_OF_BIRTH_PAST));
        }
        if (customerRequestDto.dateOfBirth().isAfter(LocalDate.now().minusYears(18)) || customerRequestDto.dateOfBirth().isBefore(LocalDate.now().minusYears(120))) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_DATE_OF_BIRTH_INVALID));
        }
        if (customerRequestDto.connectedUserRequestDto().password() == null || customerRequestDto.connectedUserRequestDto().password().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_PASSWORD_NULL));
        }
        if (!customerRequestDto.connectedUserRequestDto().password().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$")) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_PASSWORD_INVALID));
        }
        if (customerRequestDto.licenses() == null) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_LICENSES_NULL));
        }
    }


    private Customer validateCustomer(int idCustomer) {
        Optional<Customer> customerOpt = customerDao.findById(idCustomer);
        if (customerOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CUSTOMER_ID_NOT_FOUND));
        }
        return customerOpt.get();
    }
}