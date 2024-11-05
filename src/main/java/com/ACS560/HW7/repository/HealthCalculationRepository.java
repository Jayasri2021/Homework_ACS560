package com.ACS560.HW7.repository;

import com.ACS560.HW7.entities.HealthCalculationEntity;
import com.ACS560.HW7.entities.HealthCalculationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthCalculationRepository extends JpaRepository<HealthCalculationEntity, HealthCalculationEntityId> {
    List<HealthCalculationEntity> findByPatientId(Long patientId);
}