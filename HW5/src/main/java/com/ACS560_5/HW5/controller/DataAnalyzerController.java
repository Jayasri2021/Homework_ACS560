package com.ACS560_5.HW5.controller;

import com.ACS560_5.HW5.entity.HealthCalculationEntity;
import com.ACS560_5.HW5.services.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/health-calculations")
public class DataAnalyzerController {

    private final DataAnalyzerService dataAnalyzerService;

    @Autowired
    public DataAnalyzerController(DataAnalyzerService dataAnalyzerService) {
        this.dataAnalyzerService = dataAnalyzerService;
    }

    @GetMapping
    public ResponseEntity<List<HealthCalculationEntity>> getAllCalculations() {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getAllHealthCalculations();
        return ResponseEntity.ok(calculations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthCalculationEntity> getCalculationById(@PathVariable Long id) {
        Optional<HealthCalculationEntity> calculation = dataAnalyzerService.getHealthCalculationById(id);
        return calculation.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/duration/{duration}")
    public ResponseEntity<List<HealthCalculationEntity>> getCalculationsByDuration(@PathVariable int duration) {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getHealthCalculationsByDuration(duration);
        return ResponseEntity.ok(calculations);
    }

    @GetMapping("/current-pulse/{currentPulse}")
    public ResponseEntity<List<HealthCalculationEntity>> getCalculationsByCurrentPulse(@PathVariable int currentPulse) {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getHealthCalculationsByCurrentPulse(currentPulse);
        return ResponseEntity.ok(calculations);
    }

    @GetMapping("/max-pulse/{maxPulse}")
    public ResponseEntity<List<HealthCalculationEntity>> getCalculationsByMaxPulse(@PathVariable int maxPulse) {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getHealthCalculationsByMaxPulse(maxPulse);
        return ResponseEntity.ok(calculations);
    }

    @GetMapping("/durations")
    public ResponseEntity<List<HealthCalculationEntity>> getCalculationsByDurations(@RequestParam Set<Integer> durations) {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getHealthCalculationsByDurations(durations);
        return ResponseEntity.ok(calculations);
    }

    @GetMapping("/current-pulses")
    public ResponseEntity<List<HealthCalculationEntity>> getCalculationsByCurrentPulses(@RequestParam Set<Integer> currentPulses) {
        List<HealthCalculationEntity> calculations = dataAnalyzerService.getHealthCalculationsByCurrentPulses(currentPulses);
        return ResponseEntity.ok(calculations);
    }

    @PostMapping
    public ResponseEntity<HealthCalculationEntity> createCalculation(@RequestBody HealthCalculationEntity healthCalculation) {
        HealthCalculationEntity createdCalculation = dataAnalyzerService.createHealthCalculation(healthCalculation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalculation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthCalculationEntity> updateCalculation(@PathVariable Long id, @RequestBody HealthCalculationEntity healthCalculation) {
        HealthCalculationEntity updatedCalculation = dataAnalyzerService.updateHealthCalculation(id, healthCalculation);
        return updatedCalculation != null ? ResponseEntity.ok(updatedCalculation)
                                            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        dataAnalyzerService.deleteHealthCalculation(id);
        return ResponseEntity.noContent().build();
    }
}
