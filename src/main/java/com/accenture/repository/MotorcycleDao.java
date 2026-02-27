package com.accenture.repository;

import com.accenture.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleDao extends JpaRepository<Motorcycle, Integer> {
}
