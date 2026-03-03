package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.AdminMapper;
import com.accenture.model.Admin;
import com.accenture.model.Customer;
import com.accenture.repository.AdminDao;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
//        if (adminRequestDto.role() == null) {
//            throw new ConnectedUserException(messages.getMessage("admin.role.null"));
//        }
    }


}
