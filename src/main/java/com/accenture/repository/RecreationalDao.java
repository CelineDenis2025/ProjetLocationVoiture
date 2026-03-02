package com.accenture.repository;

import com.accenture.model.Recreational;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecreationalDao extends JpaRepository<Recreational, Integer> {
}
