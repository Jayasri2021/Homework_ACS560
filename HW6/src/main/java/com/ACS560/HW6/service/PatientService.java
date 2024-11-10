package com.ACS560.HW6.service;

import java.util.List;
import java.util.Optional;
import com.ACS560.HW6.model.PatientModel;
import com.ACS560.HW6.request.PatientRequest;
import jakarta.validation.Valid;

public interface PatientService {
    List<PatientModel> getPatients();
    Optional<PatientModel> getPatient(Long id);
    List<PatientModel> getPatientsByName(String name);
    PatientModel addPatient(@Valid PatientRequest patient);
    PatientModel updatePatient(Long id, @Valid PatientRequest patient);
    boolean deletePatient(Long id);
}
