package com.ACS560.HW7.repository;

import com.ACS560.HW7.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Optional<PatientEntity> findByName(String name);
}
