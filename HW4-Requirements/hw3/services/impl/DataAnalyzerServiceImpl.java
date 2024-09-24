package com.DataAnalyzer.hw3.services.impl;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import com.DataAnalyzer.hw3.repository.DataAnalyzerrepository;
import com.DataAnalyzer.hw3.services.DataAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link DataAnalyzerService} interface.
 * This service provides methods to manage {@link HealthCalculation} data, including CRUD operations 
 * and various calculation functions such as getting average calories and maximum calories burned.
 */
@Service
public class DataAnalyzerServiceImpl implements DataAnalyzerService {

    private final DataAnalyzerrepository dataAnalyzerRepository;

    /**
     * Constructor that injects the {@link DataAnalyzerrepository} dependency.
     *
     * @param dataAnalyzerRepository the repository for managing health calculation data
     */
    @Autowired
    public DataAnalyzerServiceImpl(DataAnalyzerrepository dataAnalyzerRepository) {
        this.dataAnalyzerRepository = dataAnalyzerRepository;
    }

    /**
     * Retrieves all health calculations from the repository.
     *
     * @return a list of {@link HealthCalculation} objects
     */
    @Override
    public List<HealthCalculation> getAllHealthCalculations() {
        return dataAnalyzerRepository.getAllHealthCalculations();
    }

    /**
     * Adds a new health calculation to the repository.
     *
     * @param healthCalculation the {@link HealthCalculation} object to be added
     * @return {@code true} if the record was added successfully, {@code false} if it already exists
     */
    @Override
    public boolean addHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.addHealthCalculation(healthCalculation);
    }

    /**
     * Updates an existing health calculation in the repository.
     *
     * @param healthCalculation the {@link HealthCalculation} object with updated values
     * @return {@code true} if the record was updated successfully, {@code false} if it was not found
     */
    @Override
    public boolean updateHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.updateHealthCalculation(healthCalculation);
    }

    /**
     * Deletes a health calculation from the repository.
     *
     * @param healthCalculation the {@link HealthCalculation} object to be deleted
     * @return {@code true} if the record was deleted successfully, {@code false} if it was not found
     */
    @Override
    public boolean deleteHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.deleteHealthCalculation(healthCalculation);
    }


    /**
     * Finds the maximum calories burned for a specific exercise duration and heart rate.
     *
     * @param duration  the duration of the exercise in minutes
     * @param heartRate the heart rate during the exercise
     * @return the maximum calories burned for the given duration and heart rate, or {@code 0} if there are no matching records
     */
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
