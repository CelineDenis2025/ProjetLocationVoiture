package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.mapper.AdminMapper;
import com.accenture.model.Admin;
import com.accenture.model.Customer;
import com.accenture.model.enums.Role;
import com.accenture.repository.AdminDao;
import com.accenture.repository.ConnectedUserDao;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;
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

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService{

    private final AdminDao adminDao;
    private final ConnectedUserDao connectedUserDao;
    private final MessageSourceAccessor messages;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto addAdmin(AdminRequestDto adminRequestDto) throws ConnectedUserException {

        long adminCount = adminDao.count();

        // Cas 1 : aucun admin n'existe encore → autoriser n'importe qui
        if (adminCount == 0) {
            verify(adminRequestDto);
            Admin admin = adminMapper.toAdmin(adminRequestDto);
            admin.setPassword(passwordEncoder.encode(adminRequestDto.connectedUserRequestDto().password()));
            Admin saved = adminDao.save(admin);
            return adminMapper.toAdminResponseDto(saved);
        }

        // Cas 2 : un admin existe → seuls les admins peuvent en créer un autre
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin =
                auth != null &&
                        auth.isAuthenticated() &&
                        !(auth instanceof AnonymousAuthenticationToken) &&
                        auth.getAuthorities().stream()
                                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new ConnectedUserException(messages.getMessage(Messages.CREATION_ADMIN));
        }

        // Cas 3 : admin authentifié → autorisé
        verify(adminRequestDto);
        Admin admin = adminMapper.toAdmin(adminRequestDto);
        admin.setPassword(passwordEncoder.encode(adminRequestDto.connectedUserRequestDto().password()));
        Admin saved = adminDao.save(admin);
        return adminMapper.toAdminResponseDto(saved);
    }



    @Override
    public AdminResponseDto findById(int id) {
        Admin admin = validateAdmin(id);
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public AdminResponseDto partiallyUpdateAdmin(int idAmin, AdminRequestDto adminRequestDto) {
        Admin admin = validateAdmin(idAmin);
        if (adminRequestDto.connectedUserRequestDto().firstName() != null && !adminRequestDto.connectedUserRequestDto().firstName().isBlank()){
            admin.setFirstName(adminRequestDto.connectedUserRequestDto().firstName());
        }
        if (adminRequestDto.connectedUserRequestDto().lastName() != null && !adminRequestDto.connectedUserRequestDto().lastName().isBlank()){
            admin.setLastName(adminRequestDto.connectedUserRequestDto().lastName());
        }
        if (adminRequestDto.connectedUserRequestDto().email() != null && !adminRequestDto.connectedUserRequestDto().email().isBlank()){
            admin.setEmail(adminRequestDto.connectedUserRequestDto().email());
        }
        if (adminRequestDto.connectedUserRequestDto().password() != null && !adminRequestDto.connectedUserRequestDto().password().isBlank()){
            admin.setPassword(passwordEncoder.encode(adminRequestDto.connectedUserRequestDto().password()));
        }
        if (adminRequestDto.function() != null && !adminRequestDto.function().isBlank()){
            admin.setFunction(adminRequestDto.function());
        }
        return adminMapper.toAdminResponseDto(admin);
    }

    @Override
    public void deleteAdmin(int idAdmin) throws ConnectedUserException {

        long adminCount = adminDao.count();

        if (adminCount <= 1) {
            throw new ConnectedUserException(messages.getMessage(Messages.DELETE_LAST_ADMIN));
        }

        Admin admin = validateAdmin(idAdmin);
        adminDao.delete(admin);
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


    private Admin validateAdmin(int idAmin) {
        Optional<Admin> adminOpt = adminDao.findById(idAmin);
        if (adminOpt.isEmpty()) {
            throw new ConnectedUserException(messages.getMessage(Messages.ADMIN_ID_NOT_FOUND));
        }
        return adminOpt.get();
    }
}
