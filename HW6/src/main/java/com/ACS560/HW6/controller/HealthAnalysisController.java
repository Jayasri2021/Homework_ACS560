package com.ACS560.HW6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ACS560.HW6.service.HealthAnalysisService;

/**
 * REST controller for health data analysis.
 */
@RestController
@RequestMapping("/api/v1/analysis")
@CrossOrigin(origins = "*")
public class HealthAnalysisController {

    private final HealthAnalysisService analysisService;

    @Autowired
    public HealthAnalysisController(HealthAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    /**
     * GET /api/v1/analysis/calories/average/{patientId} : Get average calories burnt
     */
    @GetMapping("/calories/average/{patientId}")
    public ResponseEntity<Double> getAverageCalories(@PathVariable Long patientId) {
        return ResponseEntity.ok(analysisService.calculateAverageCalories(patientId));
    }

    /**
     * GET /api/v1/analysis/calories/average/{patientId}/duration/{min}/{max} : 
     * Get average calories for duration range
     */
    @GetMapping("/calories/average/{patientId}/duration/{min}/{max}")
    public ResponseEntity<Double> getAverageCaloriesForDuration(
            @PathVariable Long patientId,
            @PathVariable int min,
            @PathVariable int max) {
        return ResponseEntity.ok(
            analysisService.calculateAverageCaloriesForDurationRange(patientId, min, max)
        );
    }

    /**
     * GET /api/v1/analysis/pulse/average/{patientId} : Get average pulse rate
     */
    @GetMapping("/pulse/average/{patientId}")
    public ResponseEntity<Double> getAveragePulse(@PathVariable Long patientId) {
        return ResponseEntity.ok(analysisService.calculateAveragePulseRate(patientId));
    }

    /**
     * GET /api/v1/analysis/pulse/max/average/{patientId} : Get average max pulse rate
     */
    @GetMapping("/pulse/max/average/{patientId}")
    public ResponseEntity<Double> getAverageMaxPulse(@PathVariable Long patientId) {
        return ResponseEntity.ok(analysisService.calculateAverageMaxPulseRate(patientId));
    }

    /**
     * GET /api/v1/analysis/calories/total/{patientId} : Get total calories burnt
     */
    @GetMapping("/calories/total/{patientId}")
    public ResponseEntity<Double> getTotalCalories(@PathVariable Long patientId) {
        return ResponseEntity.ok(analysisService.calculateTotalCalories(patientId));
    }
}

//{"id": 831652,
//    "name": "Alice Brown",
//    "address": "321 Pine Rd Smallville USA",
//    "healthCalculations": []}