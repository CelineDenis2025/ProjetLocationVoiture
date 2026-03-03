package com.accenture.repository;

import com.accenture.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDao extends JpaRepository<Admin, Integer> {

    boolean existsByEmail(String email);

    Optional<Admin> findById(int id);
}
