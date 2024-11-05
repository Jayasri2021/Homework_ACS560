package com.ACS560.HW6.repository;

import com.ACS560.HW6.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Optional<PatientEntity> findByName(String name);
}
