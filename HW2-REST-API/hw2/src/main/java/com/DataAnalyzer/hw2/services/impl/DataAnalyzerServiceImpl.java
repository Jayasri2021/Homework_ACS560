package com.DataAnalyzer.hw2.services.impl;

import com.DataAnalyzer.hw2.models.HealthCalculation;
import com.DataAnalyzer.hw2.repository.DataAnalyzerrepository;
import com.DataAnalyzer.hw2.services.DataAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the DataAnalyzerService interface.
 */
@Service
public class DataAnalyzerServiceImpl implements DataAnalyzerService {

    private final DataAnalyzerrepository repository;

    @Autowired
    public DataAnalyzerServiceImpl(DataAnalyzerrepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HealthCalculation> getAllHealthCalculations() {
        return repository.getAllHealthCalculations();
    }

    @Override
    public double getAverageCalories() {
        List<HealthCalculation> calculations = getAllHealthCalculations();
        if (calculations.isEmpty()) {
            return 0;
        }
        double totalCalories = calculations.stream()
                .mapToDouble(HealthCalculation::getCalories)
                .sum();
        return totalCalories / calculations.size();
    }

    @Override
    public double getMaxCaloriesBurned() {
        List<HealthCalculation> calculations = getAllHealthCalculations();
        return calculations.stream()
                .mapToDouble(HealthCalculation::getCalories)
                .max()
                .orElse(0);
    }

    @Override
    public double getMaxCaloriesBurntByDuration(int duration) {
        List<HealthCalculation> calculations = getAllHealthCalculations();
        return calculations.stream()
                .filter(calc -> calc.getDuration() == duration)
                .mapToDouble(HealthCalculation::getCalories)
                .max()
                .orElse(0);
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
