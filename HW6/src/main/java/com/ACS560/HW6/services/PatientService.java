package com.ACS560.HW6.services;

import com.ACS560.HW6.models.PatientModel;
import com.ACS560.HW6.request.PatientRequest;
import java.util.List;

public interface PatientService {
    List<PatientModel> getAllPatients();
    PatientModel getPatientById(Long id);
    PatientModel createPatient(PatientRequest request);
    PatientModel updatePatient(Long id, PatientRequest request);
    void deletePatient(Long id);
}
