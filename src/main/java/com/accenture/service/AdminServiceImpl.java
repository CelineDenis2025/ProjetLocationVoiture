package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.AdminMapper;
import com.accenture.model.Address;
import com.accenture.model.Admin;
import com.accenture.model.Customer;
import com.accenture.model.enums.Role;
import com.accenture.repository.AdminDao;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.service.dto.CustomerRequestDto;
import com.accenture.service.dto.CustomerResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService{

    private final AdminDao adminDao;
    private final MessageSourceAccessor messages;
    private final AdminMapper adminMapper;

    @Override
    public AdminResponseDto addAdmin(AdminRequestDto adminRequestDto) throws ConnectedUserException {
        verify(adminRequestDto);
        Admin admin = adminMapper.toAdmin(adminRequestDto);
        Admin saved = adminDao.save(admin);
        return adminMapper.toAdminResponseDto(saved);
    }

    @Override
    public AdminResponseDto findById(int id, String email, String password) {
        Admin admin = validateAdmin(id, email, password);
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public AdminResponseDto partiallyUpdateAdmin(int idAmin, String email, String password, AdminRequestDto adminRequestDto) {
        Admin admin = validateAdmin(idAmin, email, password);
        if (adminRequestDto.firstName() != null && !adminRequestDto.firstName().isBlank()){
            admin.setFirstName(adminRequestDto.firstName());
        }
        if (adminRequestDto.lastName() != null && !adminRequestDto.lastName().isBlank()){
            admin.setLastName(adminRequestDto.lastName());
        }
        if (adminRequestDto.email() != null && !adminRequestDto.email().isBlank()){
            admin.setEmail(adminRequestDto.email());
        }
        if (adminRequestDto.password() != null && !adminRequestDto.password().isBlank()){
            admin.setPassword(adminRequestDto.password());
        }
        if (adminRequestDto.function() != null && !adminRequestDto.function().isBlank()){
            admin.setFunction(adminRequestDto.function());
        }
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public void deleteAdmin(int idAmin, String email, String password) throws ConnectedUserException {
        validateAdmin(idAmin, email, password);
        adminDao.deleteById(idAmin);
    }


    private void verify(AdminRequestDto adminRequestDto) {
        if (adminRequestDto == null) {
            throw new ConnectedUserException(messages.getMessage("admin.null"));
        }
        if (adminRequestDto.firstName() == null || adminRequestDto.firstName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.firstname.null"));
        }
        if (adminRequestDto.lastName() == null || adminRequestDto.lastName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.lastname.null"));
        }
        if (adminRequestDto.email() == null || adminRequestDto.email().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.null"));
        }
        if (!adminRequestDto.email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.invalid"));
        }
        if (adminDao.existsByEmail(adminRequestDto.email())) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.already.exists"));
        }
        if (adminRequestDto.password() == null || adminRequestDto.password().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.null"));
        }
        if (!adminRequestDto.password().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$")) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.invalid"));
        }
        if (adminRequestDto.function() == null || adminRequestDto.function().isBlank()) {
            throw new ConnectedUserException(messages.getMessage("admin.function.null"));
        }
    }

    private Admin validateAdmin(int idAmin, String email, String password) {
        Optional<Admin> adminOpt = adminDao.findById(idAmin);
        if (adminOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage("admin.id.not.found"));
        }
        if (adminOpt.get().getRole() != Role.ADMIN) {
            throw new ConnectedUserException(messages.getMessage("admin.role.not.allowed"));
        }
        if (!adminOpt.get().getEmail().equals(email)) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.email.not.allowed"));
        }
        if (!adminOpt.get().getPassword().equals(password)) {
            throw new ConnectedUserException(messages.getMessage("connectedUser.password.not.allowed"));
        }
        return adminOpt.get();
    }
}
