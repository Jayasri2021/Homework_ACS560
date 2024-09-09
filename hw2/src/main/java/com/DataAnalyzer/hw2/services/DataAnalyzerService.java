package com.DataAnalyzer.hw2.services;

import com.DataAnalyzer.hw2.models.HealthCalculation;
import java.util.List;

/**
 * Service interface for health calculation data analysis operations.
 */
public interface DataAnalyzerService {

    /**
     * Retrieves all health calculations.
     *
     * @return List of all HealthCalculation objects
     */
    List<HealthCalculation> getAllHealthCalculations();

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