package com.DataAnalyzer.hw3.controllers;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import com.DataAnalyzer.hw3.services.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class that handles HTTP requests for health calculation operations 
 * such as retrieval, addition, updating, and deletion.
 */
@RestController
@RequestMapping("/api/data/CRUD")
public class DataAnalyzerController {

    private final DataAnalyzerService dataAnalyzerService;

    /**
     * Constructor to inject DataAnalyzerService.
     *
     * @param dataAnalyzerService The service instance to be injected.
     */
    @Autowired
    public DataAnalyzerController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    /**
     * Retrieves all health calculations.
     *
     * @return ResponseEntity containing the list of HealthCalculation objects.
     */
    @GetMapping
    public ResponseEntity<List<HealthCalculation>> getAllHealthCalculations() {
        try {
            List<HealthCalculation> calculations = dataAnalyzerService.getAllHealthCalculations();
            return new ResponseEntity<>(calculations, HttpStatus.OK);
        } catch (Exception e) {
            // Handle potential server errors
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a new health calculation.
     *
     * @param healthCalculation The HealthCalculation to be added.
     * @return ResponseEntity with appropriate HTTP status.
     */
    @PostMapping
    public ResponseEntity<String> addHealthCalculation(@RequestBody HealthCalculation healthCalculation) {
        try {
            boolean isAdded = dataAnalyzerService.addHealthCalculation(healthCalculation);
            if (isAdded) {
                return new ResponseEntity<>("Record added successfully.", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Duplicate record. Unable to add.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            // Handle potential server errors (e.g., CSV file save failure)
            return new ResponseEntity<>("Failed to save data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing health calculation.
     *
     * @param healthCalculation The HealthCalculation to be updated.
     * @return ResponseEntity with appropriate HTTP status.
     */
    @PutMapping
    public ResponseEntity<String> updateHealthCalculation(@RequestBody HealthCalculation healthCalculation) {
        try {
            boolean isUpdated = dataAnalyzerService.updateHealthCalculation(healthCalculation);
            if (isUpdated) {
                return new ResponseEntity<>("Record updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Record not found. Unable to update.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle potential server errors (e.g., CSV file save failure)
            return new ResponseEntity<>("Failed to save data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a health calculation.
     *
     * @param healthCalculation The HealthCalculation to be deleted.
     * @return ResponseEntity with appropriate HTTP status.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteHealthCalculation(@RequestBody HealthCalculation healthCalculation) {
        try {
            boolean isDeleted = dataAnalyzerService.deleteHealthCalculation(healthCalculation);
            if (isDeleted) {
                return new ResponseEntity<>("Record deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Record not found. Unable to delete.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle potential server errors (e.g., CSV file save failure)
            return new ResponseEntity<>("Failed to save data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
