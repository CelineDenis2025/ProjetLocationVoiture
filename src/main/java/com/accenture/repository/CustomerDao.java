package com.accenture.repository;

import com.accenture.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    Optional<Customer> findById(int id);

}
