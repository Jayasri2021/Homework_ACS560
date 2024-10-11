package com.ACS560HW3.HW4.services.impl;

import com.ACS560HW3.HW4.models.HealthCalculation;
import com.ACS560HW3.HW4.repository.DataAnalyzerrepository;
import com.ACS560HW3.HW4.services.DataAnalyzerService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the DataAnalyzerService interface.
 */
@Service
public class DataAnalyzerServiceImpl implements DataAnalyzerService {
   
    /**
     * Retrieves all health calculations from the repository.
     *
     * @return List of all HealthCalculation objects.
     */
    @Override
    public List<HealthCalculation> getAllHealthCalculations() {
        return DataAnalyzerrepository.getAllHealthCalculations();
    }

    /**
     * Adds a new health calculation and saves it to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be added.
     * @return true if added successfully, false otherwise.
     */
    @Override
    public boolean addHealthCalculation(HealthCalculation healthCalculation) {
        return DataAnalyzerrepository.addHealthCalculation(healthCalculation);
    }

    /**
     * Updates an existing health calculation and saves it to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be updated.
     * @return true if updated successfully, false otherwise.
     */
    @Override
    public boolean updateHealthCalculation(HealthCalculation healthCalculation) {
        return DataAnalyzerrepository.updateHealthCalculation(healthCalculation);
    }

    /**
     * Deletes an existing health calculation and saves the changes to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be deleted.
     * @return true if deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteHealthCalculation(HealthCalculation healthCalculation) {
        return DataAnalyzerrepository.deleteHealthCalculation(healthCalculation);
    }

    @Override
    public double getMaxCaloriesBurntByDurationAndHeartRate(int duration, int heartRate) {
    	List<HealthCalculation> calculations = getAllHealthCalculations();
        return calculations.stream()
                .filter(calc -> calc.getDuration() == duration && calc.getCurrentPulse() == heartRate)
                .mapToDouble(HealthCalculation::getCalories)
                .max()
                .orElse(0);
    }
}
