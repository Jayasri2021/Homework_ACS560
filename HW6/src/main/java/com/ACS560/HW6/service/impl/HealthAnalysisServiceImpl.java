package com.ACS560.HW6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ACS560.HW6.exception.ResourceNotFoundException;
import com.ACS560.HW6.repository.HealthCalculationRepository;
import com.ACS560.HW6.repository.PatientRepository;
import com.ACS560.HW6.service.HealthAnalysisService;

@Service
@Transactional(readOnly = true)
public class HealthAnalysisServiceImpl implements HealthAnalysisService {

    private final HealthCalculationRepository healthCalculationRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HealthAnalysisServiceImpl(
            HealthCalculationRepository healthCalculationRepository,
            PatientRepository patientRepository) {
        this.healthCalculationRepository = healthCalculationRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public double calculateAverageCalories(Long patientId) {
        validatePatient(patientId);
        Double average = healthCalculationRepository.calculateAverageCaloriesByPatient(patientId);
        return average != null ? average : 0.0;
    }

    @Override
    public double calculateAverageCaloriesForDurationRange(Long patientId, int minDuration, int maxDuration) {
        validatePatient(patientId);
        if (minDuration > maxDuration) {
            throw new IllegalArgumentException("Minimum duration cannot be greater than maximum duration");
        }
        Double average = healthCalculationRepository.calculateAverageCaloriesForDurationRange(
            patientId, minDuration, maxDuration);
        return average != null ? average : 0.0;
    }

    @Override
    public double calculateAveragePulseRate(Long patientId) {
        validatePatient(patientId);
        Double average = healthCalculationRepository.calculateAverageCurrentPulseByPatient(patientId);
        return average != null ? average : 0.0;
    }

    @Override
    public double calculateAverageMaxPulseRate(Long patientId) {
        validatePatient(patientId);
        Double average = healthCalculationRepository.calculateAverageMaxPulseByPatient(patientId);
        return average != null ? average : 0.0;
    }

    @Override
    public double calculateTotalCalories(Long patientId) {
        validatePatient(patientId);
        Double total = healthCalculationRepository.calculateTotalCaloriesByPatient(patientId);
        return total != null ? total : 0.0;
    }

    private void validatePatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
    }
}