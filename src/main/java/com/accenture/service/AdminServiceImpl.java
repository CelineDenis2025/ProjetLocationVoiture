package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.AdminMapper;
import com.accenture.model.Admin;
import com.accenture.model.enums.Role;
import com.accenture.repository.AdminDao;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
import com.accenture.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService{

    private final AdminDao adminDao;
    private final MessageSourceAccessor messages;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto addAdmin(AdminRequestDto adminRequestDto) throws ConnectedUserException {
        verify(adminRequestDto);
        Admin admin = adminMapper.toAdmin(adminRequestDto);
        admin.setPassword(passwordEncoder.encode(adminRequestDto.connectedUserRequestDto().password()));
        Admin saved = adminDao.save(admin);
        return adminMapper.toAdminResponseDto(saved);
    }

    @Override
    public AdminResponseDto findById(int id, String email) {
        Admin admin = validateAdmin(id, email);
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public AdminResponseDto partiallyUpdateAdmin(int idAmin, String email, AdminRequestDto adminRequestDto) {
        Admin admin = validateAdmin(idAmin, email);
        if (adminRequestDto.connectedUserRequestDto().firstName() != null && !adminRequestDto.connectedUserRequestDto().firstName().isBlank()){
            admin.setFirstName(adminRequestDto.connectedUserRequestDto().firstName());
        }
        if (adminRequestDto.connectedUserRequestDto().lastName() != null && !adminRequestDto.connectedUserRequestDto().lastName().isBlank()){
            admin.setLastName(adminRequestDto.connectedUserRequestDto().lastName());
        }
        if (adminRequestDto.connectedUserRequestDto().email() != null && !adminRequestDto.connectedUserRequestDto().email().isBlank()){
            admin.setEmail(adminRequestDto.connectedUserRequestDto().email());
        }
        if (adminRequestDto.function() != null && !adminRequestDto.function().isBlank()){
            admin.setFunction(adminRequestDto.function());
        }
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public void deleteAdmin(int idAmin, String email) throws ConnectedUserException {
        validateAdmin(idAmin, email);
        adminDao.deleteById(idAmin);
    }


    private void verify(AdminRequestDto adminRequestDto) {
        if (adminRequestDto == null) {
            throw new ConnectedUserException(messages.getMessage(Messages.ADMIN_NULL));
        }
        if (adminRequestDto.connectedUserRequestDto().firstName() == null || adminRequestDto.connectedUserRequestDto().firstName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_FIRSTNAME_NULL));
        }
        if (adminRequestDto.connectedUserRequestDto().lastName() == null || adminRequestDto.connectedUserRequestDto().lastName().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_LASTNAME_NULL));
        }
        if (adminRequestDto.connectedUserRequestDto().email() == null || adminRequestDto.connectedUserRequestDto().email().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_NULL));
        }
        if (!adminRequestDto.connectedUserRequestDto().email().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_INVALID));
        }
        if (adminDao.existsByEmail(adminRequestDto.connectedUserRequestDto().email())) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_ALREADY_EXIST));
        }
        if (adminRequestDto.connectedUserRequestDto().password() == null || adminRequestDto.connectedUserRequestDto().password().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_PASSWORD_NULL));
        }
        if (!adminRequestDto.connectedUserRequestDto().password().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[&#@\\-_§])[A-Za-z\\d&#@\\-_§]{8,16}$")) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_PASSWORD_INVALID));
        }
        if (adminRequestDto.function() == null || adminRequestDto.function().isBlank()) {
            throw new ConnectedUserException(messages.getMessage(Messages.ADMIN_FUNCTION_NULL));
        }
    }


    private Admin validateAdmin(int idAmin, String email) {
        Optional<Admin> adminOpt = adminDao.findById(idAmin);
        if (adminOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage(Messages.ADMIN_ID_NOT_FOUND));
        }
        if (adminOpt.get().getRole() != Role.ADMIN) {
            throw new ConnectedUserException(messages.getMessage(Messages.ADMIN_ROLE_NOT_ALLOWED));
        }
        if (!adminOpt.get().getEmail().equals(email)) {
            throw new ConnectedUserException(messages.getMessage(Messages.CONNECTED_USER_EMAIL_NOT_ALLOWED));
        }
        return adminOpt.get();
    }
}
