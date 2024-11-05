package com.ACS560.HW7.services;

import com.ACS560.HW7.models.HealthCalculationModel;
import java.util.List;

public interface HealthCalculationService {
    List<HealthCalculationModel> getAllCalculations();
    HealthCalculationModel getCalculationById(Long id, Long patientId);
    List<HealthCalculationModel> getCalculationsByPatientId(Long patientId);
    HealthCalculationModel createCalculation(HealthCalculationModel model);
    void deleteCalculation(Long id, Long patientId);
}
