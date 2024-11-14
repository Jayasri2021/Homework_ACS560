package com.ACS560.HW6.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.ACS560.HW6.entity.PatientEntity;
import com.ACS560.HW6.exception.ResourceNotFoundException;
import com.ACS560.HW6.model.PatientModel;
import com.ACS560.HW6.repository.PatientRepository;
import com.ACS560.HW6.request.PatientRequest;
import com.ACS560.HW6.service.PatientService;

import jakarta.validation.Valid;

@Service
@Transactional
@Validated
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientModel> getPatients() {
        return patientRepository.findAllModelsOrderByName();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PatientModel> getPatient(Long id) {
        return patientRepository.findById(id)
            .map(this::convertToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientModel> getPatientsByName(String name) {
        return patientRepository.findModelsByNameContaining(name);
    }

    @Override
    public PatientModel addPatient(@Valid PatientRequest request) {
        if (patientRepository.existsByName(request.getName())) {
            throw new IllegalStateException("Patient name already exists: " + request.getName());
        }

        PatientEntity patient = new PatientEntity();
        patient.setId(request.getId());
        patient.setName(request.getName());
        patient.setAddress(request.getAddress());
        
        PatientEntity savedPatient = patientRepository.save(patient);
        return convertToModel(savedPatient);
    }

    @Override
    public PatientModel updatePatient(Long id, @Valid PatientRequest request) {
        return patientRepository.findById(id)
            .map(patient -> {
                if (!patient.getName().equals(request.getName()) && 
                    patientRepository.existsByName(request.getName())) {
                    throw new IllegalStateException("Patient name already exists: " + request.getName());
                }
                
                patient.setName(request.getName());
                patient.setAddress(request.getAddress());
                
                PatientEntity updatedPatient = patientRepository.save(patient);
                return convertToModel(updatedPatient);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PatientModel convertToModel(PatientEntity entity) {
        PatientModel model = new PatientModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setAddress(entity.getAddress());
        return model;
    }
}