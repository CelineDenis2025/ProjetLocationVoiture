package com.accenture.service;

import com.accenture.exception.ConnectedUserException;
import com.accenture.service.dto.AdminRequestDto;
import com.accenture.service.dto.AdminResponseDto;

public interface AdminService {

    AdminResponseDto addAdmin(AdminRequestDto adminRequestDto) throws ConnectedUserException;

}
