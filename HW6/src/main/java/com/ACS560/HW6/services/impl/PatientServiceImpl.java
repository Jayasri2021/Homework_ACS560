package com.ACS560.HW6.services.impl;
import com.ACS560.HW6.entity.PatientEntity;
import com.ACS560.HW6.models.PatientModel;
import com.ACS560.HW6.repository.PatientRepository;
import com.ACS560.HW6.request.PatientRequest;
import com.ACS560.HW6.services.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PatientServiceImpl implements PatientService {
   private final PatientRepository patientRepository;
   public PatientServiceImpl(PatientRepository patientRepository) {
       this.patientRepository = patientRepository;
   }
   @Override
   public List<PatientModel> getAllPatients() {
       return patientRepository.findAll().stream()
           .map(this::convertToModel)
           .collect(Collectors.toList());
   }
   @Override
   public PatientModel getPatientById(Long id) {
       return patientRepository.findById(id)
           .map(this::convertToModel)
           .orElseThrow(() -> new RuntimeException("Patient not found"));
   }
   @Override
   public PatientModel createPatient(PatientRequest request) {
       PatientEntity entity = new PatientEntity();
       entity.setName(request.getName());
       entity.setAddress(request.getAddress());
       return convertToModel(patientRepository.save(entity));
   }
   @Override
   public PatientModel updatePatient(Long id, PatientRequest request) {
       PatientEntity entity = patientRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("Patient not found"));
       entity.setName(request.getName());
       entity.setAddress(request.getAddress());
       return convertToModel(patientRepository.save(entity));
   }
   @Override
   public void deletePatient(Long id) {
       patientRepository.deleteById(id);
   }
   private PatientModel convertToModel(PatientEntity entity) {
       PatientModel model = new PatientModel();
       model.setId(entity.getId());
       model.setName(entity.getName());
       model.setAddress(entity.getAddress());
       return model;
   }
}

