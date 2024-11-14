package com.ACS560.HW6.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.ACS560.HW6.entity.HealthCalculationEntity;
import com.ACS560.HW6.entity.HealthCalculationEntityId;
import com.ACS560.HW6.entity.PatientEntity;
import com.ACS560.HW6.exception.ResourceNotFoundException;
import com.ACS560.HW6.model.HealthCalculationModel;
import com.ACS560.HW6.repository.HealthCalculationRepository;
import com.ACS560.HW6.repository.PatientRepository;
import com.ACS560.HW6.request.HealthCalculationRequest;
import com.ACS560.HW6.service.HealthCalculationService;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
public class HealthCalculationServiceImpl implements HealthCalculationService {

    private final HealthCalculationRepository healthCalculationRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public HealthCalculationServiceImpl(
            HealthCalculationRepository healthCalculationRepository,
            PatientRepository patientRepository) {
        this.healthCalculationRepository = healthCalculationRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthCalculationModel> getHealthCalculations() {
        return healthCalculationRepository.findAllModels();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthCalculationModel> getHealthCalculationsByPatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
        return healthCalculationRepository.findModelsByPatientId(patientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthCalculationModel> getHealthCalculationsByDurationRange(
            Integer minDuration, Integer maxDuration) {
        if (minDuration > maxDuration) {
            throw new IllegalArgumentException("Minimum duration cannot be greater than maximum duration");
        }
        return healthCalculationRepository.findModelsByDurationBetween(minDuration, maxDuration);
    }

    @Override
    public HealthCalculationModel add(@Valid HealthCalculationRequest request) {
        PatientEntity patient = patientRepository.findById(request.getPatientId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        validatePulseRates(request.getCurrentPulse(), request.getMaxPulse());

        HealthCalculationEntity calculation = new HealthCalculationEntity();
        calculation.setId(request.getId());
        calculation.setPatientId(patient.getId());
        calculation.setDuration(request.getDuration());
        calculation.setCurrentPulse(request.getCurrentPulse());
        calculation.setMaxPulse(request.getMaxPulse());
        calculation.setCaloriesBurnt(request.getCaloriesBurnt());

        HealthCalculationEntity savedCalculation = healthCalculationRepository.save(calculation);
        return convertToModel(savedCalculation);
    }

    @Override
    public HealthCalculationModel update(@Valid HealthCalculationRequest request) {
        if (!patientRepository.existsById(request.getPatientId())) {
            throw new ResourceNotFoundException("Patient not found with id: " + request.getPatientId());
        }

        HealthCalculationEntityId id = new HealthCalculationEntityId(request.getId(), request.getPatientId());
        HealthCalculationEntity calculation = healthCalculationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Calculation not found with id: " + id));

        validatePulseRates(request.getCurrentPulse(), request.getMaxPulse());

        calculation.setDuration(request.getDuration());
        calculation.setCurrentPulse(request.getCurrentPulse());
        calculation.setMaxPulse(request.getMaxPulse());
        calculation.setCaloriesBurnt(request.getCaloriesBurnt());

        HealthCalculationEntity updatedCalculation = healthCalculationRepository.save(calculation);
        return convertToModel(updatedCalculation);
    }

    @Override
    public boolean delete(Long patientId, Long id) {
        HealthCalculationEntityId calculationId = new HealthCalculationEntityId(id, patientId);
        if (!healthCalculationRepository.existsById(calculationId)) {
            return false;
        }
        healthCalculationRepository.deleteById(calculationId);
        return true;
    }

    private void validatePulseRates(Integer currentPulse, Integer maxPulse) {
        if (currentPulse > maxPulse) {
            throw new IllegalArgumentException("Current pulse cannot be greater than max pulse");
        }
    }

    private HealthCalculationModel convertToModel(HealthCalculationEntity entity) {
        return new HealthCalculationModel(
            entity.getId(),
            entity.getPatientId(),
            entity.getDuration(),
            entity.getCurrentPulse(),
            entity.getMaxPulse(),
            entity.getCaloriesBurnt()
        );
    }
}