package com.ACS560_5.HW5.services;

import com.ACS560_5.HW5.entity.HealthCalculationEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DataAnalyzerService {

    List<HealthCalculationEntity> getAllHealthCalculations();

    Optional<HealthCalculationEntity> getHealthCalculationById(Long id);

    List<HealthCalculationEntity> getHealthCalculationsByDuration(int duration);

    List<HealthCalculationEntity> getHealthCalculationsByCurrentPulse(int currentPulse);
    
    List<HealthCalculationEntity> getHealthCalculationsByMaxPulse(int maxPulse);

    List<HealthCalculationEntity> getHealthCalculationsByDurations(Set<Integer> durations);

    List<HealthCalculationEntity> getHealthCalculationsByCurrentPulses(Set<Integer> currentPulses);

    HealthCalculationEntity createHealthCalculation(HealthCalculationEntity healthCalculation);

    HealthCalculationEntity updateHealthCalculation(Long id, HealthCalculationEntity healthCalculation);

    void deleteHealthCalculation(Long id);
}
