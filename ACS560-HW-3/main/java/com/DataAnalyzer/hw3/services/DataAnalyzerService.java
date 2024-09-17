package com.DataAnalyzer.hw3.services;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import java.util.List;

/**
 * Service interface for performing operations like addition, updating, deletion and retrieving. 
 */
public interface DataAnalyzerService {

    /**
     * Retrieves all health calculations.
     *
     * @return List of all HealthCalculation objects
     */
    List<HealthCalculation> getAllHealthCalculations();

    /**
     * Adds a new health calculation to the system.
     *
     * @param healthCalculation The HealthCalculation to be added.
     * @return true if added successfully, false otherwise.
     */
    boolean addHealthCalculation(HealthCalculation healthCalculation);

    /**
     * Updates an existing health calculation.
     *
     * @param healthCalculation The HealthCalculation to be updated.
     * @return true if updated successfully, false otherwise.
     */
    boolean updateHealthCalculation(HealthCalculation healthCalculation);

    /**
     * Deletes a health calculation.
     *
     * @param healthCalculation The HealthCalculation to be deleted.
     * @return true if deleted successfully, false otherwise.
     */
    boolean deleteHealthCalculation(HealthCalculation healthCalculation);

    
    // Previous Analysis of data: 
    
    
    /**
     * Gets the total number of health calculations.
     *
     * @return The total number of HealthCalculation records
     */
    int getDataSize();

    /**
     * Calculates the average calories burned across all health calculations.
     *
     * @return The average calories burned
     */
    double getAverageCalories();

    /**
     * Finds the maximum calories burned in a single health calculation.
     *
     * @return The maximum calories burned
     */
    double getMaxCaloriesBurned();

    /**
     * Finds the maximum calories burned for a specific duration.
     *
     * @param duration The duration to search for
     * @return The maximum calories burned for the given duration
     */
    double getMaxCaloriesBurntByDuration(int duration);

    /**
     * Finds the maximum calories burned for a specific duration and heart rate.
     *
     * @param duration The duration to search for
     * @param heartRate The heart rate to search for
     * @return The maximum calories burned for the given duration and heart rate
     */
    double getMaxCaloriesBurntByDurationAndHeartRate(int duration, int heartRate);
}