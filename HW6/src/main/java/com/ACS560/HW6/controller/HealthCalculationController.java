package com.ACS560.HW6.controller;

import com.ACS560.HW6.models.HealthCalculationModel;
import com.ACS560.HW6.services.HealthCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calculations")
public class HealthCalculationController {
    private final HealthCalculationService healthCalculationService;

    public HealthCalculationController(HealthCalculationService healthCalculationService) {
        this.healthCalculationService = healthCalculationService;
    }

    @GetMapping
    public ResponseEntity<List<HealthCalculationModel>> getAllCalculations() {
        return ResponseEntity.ok(healthCalculationService.getAllCalculations());
    }

    @GetMapping("/{id}/patient/{patientId}")
    public ResponseEntity<HealthCalculationModel> getCalculationById(
            @PathVariable Long id,
            @PathVariable Long patientId) {
        return ResponseEntity.ok(healthCalculationService.getCalculationById(id, patientId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<HealthCalculationModel>> getCalculationsByPatientId(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(healthCalculationService.getCalculationsByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<HealthCalculationModel> createCalculation(
            @RequestBody HealthCalculationModel model) {
        return ResponseEntity.ok(healthCalculationService.createCalculation(model));
    }

    @DeleteMapping("/{id}/patient/{patientId}")
    public ResponseEntity<Void> deleteCalculation(
            @PathVariable Long id,
            @PathVariable Long patientId) {
        healthCalculationService.deleteCalculation(id, patientId);
        return ResponseEntity.ok().build();
    }
}

