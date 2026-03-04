package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.CustomerMapper;
import com.accenture.model.Address;
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
    public CustomerResponseDto findById(int id, String email, String password) {
        Customer customer = validateCustomer(id, email, password);
        return customerMapper.toCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto partiallyUpdateCustomer(int idCustomer, String email, String password, CustomerRequestDto customerRequestDto) {
       Customer customer = validateCustomer(idCustomer, email, password);
        if (customerRequestDto.firstName() != null && !customerRequestDto.firstName().isBlank()){
           customer.setFirstName(customerRequestDto.firstName());
        }
        if (customerRequestDto.lastName() != null && !customerRequestDto.lastName().isBlank()){
            customer.setLastName(customerRequestDto.lastName());
        }
        if (customerRequestDto.email() != null && !customerRequestDto.email().isBlank()){
            customer.setEmail(customerRequestDto.email());
        }
        if (customerRequestDto.password() != null && !customerRequestDto.password().isBlank()){
            customer.setPassword(customerRequestDto.password());
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
    public void deleteCustomer(int idCustomer, String email, String password) throws ConnectedUserException {
        validateCustomer(idCustomer, email, password);
        customerDao.deleteById(idCustomer);
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
        if (customerRequestDto.password() == null || customerRequestDto.password().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.null"));
        }
        if (!customerRequestDto.password().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$")) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.invalid"));
        }
        if (customerRequestDto.licenses() == null) {
            throw new ConnectedUserException(messages.getMessage("customer.licenses.null"));
        }
    }


    private Customer validateCustomer(int idCustomer, String email, String password) {
        Optional<Customer> customerOpt = customerDao.findById(idCustomer);
        if (customerOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage("customer.id.not.found"));
        }
        if (customerOpt.get().getRole() != Role.USER) {
            throw new ConnectedUserException(messages.getMessage("customer.role.not.allowed"));
        }
        if (!customerOpt.get().getEmail().equals(email)) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.not.allowed"));
        }
        if (!customerOpt.get().getPassword().equals(password)) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.not.allowed"));
        }
        return customerOpt.get();
    }
}