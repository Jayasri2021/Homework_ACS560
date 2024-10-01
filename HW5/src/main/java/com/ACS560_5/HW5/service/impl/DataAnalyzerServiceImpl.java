package com.ACS560_5.HW5.service.impl;

import com.ACS560_5.HW5.entity.HealthCalculationEntity;
import com.ACS560_5.HW5.repository.DataAnalyzerrepository;
import com.ACS560_5.HW5.services.DataAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DataAnalyzerServiceImpl implements DataAnalyzerService {

    private final DataAnalyzerrepository dataAnalyzerRepository;

    @Autowired
    public DataAnalyzerServiceImpl(DataAnalyzerrepository dataAnalyzerRepository) {
        this.dataAnalyzerRepository = dataAnalyzerRepository;
    }

    @Override
    public List<HealthCalculationEntity> getAllHealthCalculations() {
        return (List<HealthCalculationEntity>) dataAnalyzerRepository.findAll();
    }

    @Override
    public Optional<HealthCalculationEntity> getHealthCalculationById(Long id) {
        return dataAnalyzerRepository.findById(id);
    }

    @Override
    public List<HealthCalculationEntity> getHealthCalculationsByDuration(int duration) {
        return dataAnalyzerRepository.findAllByDuration(duration);
    }

    @Override
    public List<HealthCalculationEntity> getHealthCalculationsByCurrentPulse(int currentPulse) {
        return dataAnalyzerRepository.findAllByCurrentPulse(currentPulse);
    }

    @Override
    public List<HealthCalculationEntity> getHealthCalculationsByMaxPulse(int maxPulse) {
        return dataAnalyzerRepository.findAllByMaxPulse(maxPulse);
    }

    @Override
    public List<HealthCalculationEntity> getHealthCalculationsByDurations(Set<Integer> durations) {
        return dataAnalyzerRepository.findAllByDurationIn(durations);
    }

    @Override
    public List<HealthCalculationEntity> getHealthCalculationsByCurrentPulses(Set<Integer> currentPulses) {
        return dataAnalyzerRepository.findAllByCurrentPulseIn(currentPulses);
    }

    @Override
    public HealthCalculationEntity createHealthCalculation(HealthCalculationEntity healthCalculation) {
        return dataAnalyzerRepository.save(healthCalculation);
    }

    @Override
    public HealthCalculationEntity updateHealthCalculation(Long id, HealthCalculationEntity healthCalculation) {
        if (dataAnalyzerRepository.existsById(id)) {
            healthCalculation.setId(id);
            return dataAnalyzerRepository.save(healthCalculation);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteHealthCalculation(Long id) {
        dataAnalyzerRepository.deleteById(id);
    }
}
