package com.DataAnalyzer.hw3.controllers;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import com.DataAnalyzer.hw3.services.DataAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling CRUD operations and search filters for HealthCalculation data.
 */
@RestController
@RequestMapping("/api/data/getAll")
public class DataController {

    private final DataAnalyzerService dataAnalyzerService;

    @Autowired
    public DataController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    /**
     * Gets all health calculations.
     *
     * @return List of all HealthCalculation objects
     */
    @GetMapping
    public List<HealthCalculation> getAllHealthCalculations() {
        return dataAnalyzerService.getAllHealthCalculations();
    }

    /**
     * Gets health calculations filtered by duration and heart rate.
     *
     * @param duration The duration to filter by
     * @param heartRate The heart rate to filter by
     * @return List of HealthCalculation objects with the specified duration and heart rate
     */
    @GetMapping("/duration/{duration}/heart-rate/{heartRate}")
    public List<HealthCalculation> getHealthCalculationsByDurationAndHeartRate(
            @PathVariable int duration,
            @PathVariable int heartRate) {
        return dataAnalyzerService.getAllHealthCalculations().stream()
                .filter(calc -> calc.getDuration() == duration && calc.getCurrentPulse() == heartRate)
                .toList();
    }
}
