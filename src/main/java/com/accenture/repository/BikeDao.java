package com.accenture.repository;

import com.accenture.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeDao extends JpaRepository<Bike, Integer> {
}
