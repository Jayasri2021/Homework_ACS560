package com.DataAnalyzer.hw3.controllers;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import com.DataAnalyzer.hw3.services.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing health calculation operations such as retrieval, addition, updating, and deletion.
 * This controller exposes CRUD operations through REST API endpoints for the {@link HealthCalculation} model.
 */
@RestController
@RequestMapping("/api/data/CRUD")
public class DataAnalyzerController {

    private final DataAnalyzerService dataAnalyzerService;

    /**
     * Constructor for {@code DataAnalyzerController} that injects the service layer.
     *
     * @param dataAnalyzerService the service that handles business logic for health calculations
     */
    @Autowired
    public DataAnalyzerController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    /**
     * Retrieves a list of all health calculations stored in the system.
     *
     * @return a {@link ResponseEntity} containing a list of {@link HealthCalculation} objects and an HTTP status code
     */
    @GetMapping
    public ResponseEntity<List<HealthCalculation>> getAllHealthCalculations() {
        try {
            List<HealthCalculation> calculations = dataAnalyzerService.getAllHealthCalculations();
            return new ResponseEntity<>(calculations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a new health calculation to the system.
     * 
     * If the record is successfully added, it returns a CREATED status; otherwise, it returns a BAD_REQUEST or INTERNAL_SERVER_ERROR.
     *
     * @param healthCalculation the {@link HealthCalculation} object to be added
     * @return a {@link ResponseEntity} with a success or error message and an HTTP status code
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
            return new ResponseEntity<>("Failed to save data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing health calculation.
     * 
     * If the record is successfully updated, it returns an OK status; otherwise, it returns a NOT_FOUND or INTERNAL_SERVER_ERROR.
     *
     * @param healthCalculation the {@link HealthCalculation} object containing updated values
     * @return a {@link ResponseEntity} with a success or error message and an HTTP status code
     */
    @PutMapping
    public ResponseEntity<String> updateHealthCalculation(@RequestBody HealthCalculation healthCalculation) {
        try {
            boolean isUpdated = dataAnalyzerService.updateHealthCalculation(healthCalculation);
            if (isUpdated) {
                return new ResponseEntity<>("Record updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Record not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an existing health calculation from the system.
     * 
     * If the record is successfully deleted, it returns an OK status; otherwise, it returns a NOT_FOUND or INTERNAL_SERVER_ERROR.
     *
     * @param healthCalculation the {@link HealthCalculation} object to be deleted
     * @return a {@link ResponseEntity} with a success or error message and an HTTP status code
     */
    @DeleteMapping
    public ResponseEntity<String> deleteHealthCalculation(@RequestBody HealthCalculation healthCalculation) {
        try {
            boolean isDeleted = dataAnalyzerService.deleteHealthCalculation(healthCalculation);
            if (isDeleted) {
                return new ResponseEntity<>("Record deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Record not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
