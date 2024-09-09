package com.DataAnalyzer.hw2.controllers;

import com.DataAnalyzer.hw2.services.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for performing data analysis operations on HealthCalculation data.
 */
@RestController
@RequestMapping("/api/data")
public class DataAnalyzerController {

    private final DataAnalyzerService dataAnalyzerService;

    @Autowired
    public DataAnalyzerController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    /**
     * Gets the average calories burned.
     *
     * @return The average calories burned
     */
    @GetMapping("/average-calories")
    public double getAverageCalories() {
        return dataAnalyzerService.getAverageCalories();
    }

    /**
     * Gets the maximum calories burned.
     *
     * @return The maximum calories burned
     */
    @GetMapping("/max-calories-burned")
    public double getMaxCaloriesBurned() {
        return dataAnalyzerService.getMaxCaloriesBurned();
    }

    /**
     * Gets the maximum calories burned for a specific duration.
     *
     * @param duration The duration to search for
     * @return The maximum calories burned for the given duration
     */
    @GetMapping("/{duration}")
    public double getMaxCaloriesBurntByDuration(@PathVariable int duration) {
        return dataAnalyzerService.getMaxCaloriesBurntByDuration(duration);
    }

    /**
     * Gets the maximum calories burned for a specific duration and heart rate.
     *
     * @param duration The duration to search for
     * @param heartRate The heart rate to search for
     * @return The maximum calories burned for the given duration and heart rate
     */
    @GetMapping("/{duration}/{heartRate}")
    public double getMaxCaloriesBurntByDurationAndHeartRate(
            @PathVariable int duration,
            @PathVariable int heartRate) {
        return dataAnalyzerService.getMaxCaloriesBurntByDurationAndHeartRate(duration, heartRate);
    }
}
