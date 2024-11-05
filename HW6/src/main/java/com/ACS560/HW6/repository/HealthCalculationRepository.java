package com.ACS560.HW6.repository;

import com.ACS560.HW6.entity.HealthCalculationEntity;
import com.ACS560.HW6.entity.HealthCalculationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthCalculationRepository extends JpaRepository<HealthCalculationEntity, HealthCalculationEntityId> {
    List<HealthCalculationEntity> findByPatientId(Long patientId);
}