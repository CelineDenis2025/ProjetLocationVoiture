package com.accenture.repository;

import com.accenture.model.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommercialDao extends JpaRepository<Commercial,Integer> {
}
