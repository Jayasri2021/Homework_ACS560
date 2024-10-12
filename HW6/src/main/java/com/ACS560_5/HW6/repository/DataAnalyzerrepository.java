package com.ACS560_5.HW6.repository;

import org.springframework.data.repository.CrudRepository;

import com.ACS560_5.HW6.entity.HealthCalculationEntity;

import java.util.List;
import java.util.Set;

public interface DataAnalyzerrepository extends CrudRepository<HealthCalculationEntity, Long> {
    
    // Find all health calculations by duration
    List<HealthCalculationEntity> findAllByDuration(int duration);
    
    // Find all health calculations by current pulse
    List<HealthCalculationEntity> findAllByCurrentPulse(int currentPulse);
    
    // Find all health calculations by max pulse
    List<HealthCalculationEntity> findAllByMaxPulse(int maxPulse);
    
    // Find all health calculations by duration in a set of values
    List<HealthCalculationEntity> findAllByDurationIn(Set<Integer> durations);
    
    // Find all health calculations by current pulse in a set of values
    List<HealthCalculationEntity> findAllByCurrentPulseIn(Set<Integer> currentPulses);
}
