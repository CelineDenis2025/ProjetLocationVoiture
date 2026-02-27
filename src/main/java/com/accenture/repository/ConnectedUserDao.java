package com.accenture.repository;

import com.accenture.model.ConnectedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectedUserDao extends JpaRepository<ConnectedUser, Integer> {
}
