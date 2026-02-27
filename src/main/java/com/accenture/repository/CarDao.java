package com.accenture.repository;

import com.accenture.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car,Integer> {
}
