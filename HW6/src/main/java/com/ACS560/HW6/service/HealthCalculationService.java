package com.ACS560.HW6.service;

import java.util.List;
import com.ACS560.HW6.model.HealthCalculationModel;
import com.ACS560.HW6.request.HealthCalculationRequest;
import jakarta.validation.Valid;

public interface HealthCalculationService {
    List<HealthCalculationModel> getHealthCalculations();
    List<HealthCalculationModel> getHealthCalculationsByPatient(Long patientId);
    List<HealthCalculationModel> getHealthCalculationsByDurationRange(Integer minDuration, Integer maxDuration);
    HealthCalculationModel add(@Valid HealthCalculationRequest calculation);
    HealthCalculationModel update(@Valid HealthCalculationRequest calculation);
    boolean delete(Long patientId, Long id);
}
