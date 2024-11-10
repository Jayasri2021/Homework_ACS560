package com.ACS560.HW6.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ACS560.HW6.model.HealthCalculationModel;
import com.ACS560.HW6.request.HealthCalculationRequest;
import com.ACS560.HW6.service.HealthCalculationService;

import jakarta.validation.Valid;

/**
 * REST controller for managing health calculations.
 */
@RestController
@RequestMapping("/api/v1/calculations")
@CrossOrigin(origins = "*")
public class HealthCalculationController {

    private final HealthCalculationService calculationService;

    @Autowired
    public HealthCalculationController(HealthCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    /**
     * GET /api/v1/calculations : Get all health calculations
     */
    @GetMapping
    public ResponseEntity<List<HealthCalculationModel>> getAllCalculations() {
        List<HealthCalculationModel> calculations = calculationService.getHealthCalculations();
        return ResponseEntity.ok(calculations);
    }

    /**
     * GET /api/v1/calculations/patient/{patientId} : Get calculations for a specific patient
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<HealthCalculationModel>> getCalculationsByPatient(
            @PathVariable Long patientId) {
        List<HealthCalculationModel> calculations = 
            calculationService.getHealthCalculationsByPatient(patientId);
        return ResponseEntity.ok(calculations);
    }

    /**
     * GET /api/v1/calculations/duration/{min}/{max} : Get calculations by duration range
     */
    @GetMapping("/duration/{min}/{max}")
    public ResponseEntity<List<HealthCalculationModel>> getCalculationsByDuration(
            @PathVariable Integer min,
            @PathVariable Integer max) {
        List<HealthCalculationModel> calculations = 
            calculationService.getHealthCalculationsByDurationRange(min, max);
        return ResponseEntity.ok(calculations);
    }

    /**
     * POST /api/v1/calculations : Create a new health calculation
     */
    @PostMapping
    public ResponseEntity<HealthCalculationModel> createCalculation(
            @Valid @RequestBody HealthCalculationRequest request) {
        HealthCalculationModel createdCalculation = calculationService.add(request);
        
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/patient/{patientId}/calculation/{id}")
            .buildAndExpand(createdCalculation.getPatientId(), createdCalculation.getId())
            .toUri();
            
        return ResponseEntity.created(location).body(createdCalculation);
    }

    /**
     * PUT /api/v1/calculations : Update an existing health calculation
     */
    @PutMapping
    public ResponseEntity<HealthCalculationModel> updateCalculation(
            @Valid @RequestBody HealthCalculationRequest request) {
        HealthCalculationModel updatedCalculation = calculationService.update(request);
        return ResponseEntity.ok(updatedCalculation);
    }

    /**
     * DELETE /api/v1/calculations/patient/{patientId}/calculation/{id} : Delete a calculation
     */
    @DeleteMapping("/patient/{patientId}/calculation/{id}")
    public ResponseEntity<Void> deleteCalculation(
            @PathVariable Long patientId,
            @PathVariable Long id) {
        boolean deleted = calculationService.delete(patientId, id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
