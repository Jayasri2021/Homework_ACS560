package com.ACS560.HW7.services.impl;
import com.ACS560.HW7.entities.HealthCalculationEntity;
import com.ACS560.HW7.entities.HealthCalculationEntityId;
import com.ACS560.HW7.models.HealthCalculationModel;
import com.ACS560.HW7.repository.HealthCalculationRepository;
import com.ACS560.HW7.repository.PatientRepository;
import com.ACS560.HW7.services.HealthCalculationService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class HealthCalculationServiceImpl implements HealthCalculationService {
   private final HealthCalculationRepository healthCalculationRepository;
   private final PatientRepository patientRepository;
   public HealthCalculationServiceImpl(
           HealthCalculationRepository healthCalculationRepository,
           PatientRepository patientRepository) {
       this.healthCalculationRepository = healthCalculationRepository;
       this.patientRepository = patientRepository;
   }
   @Override
   public List<HealthCalculationModel> getAllCalculations() {
       return healthCalculationRepository.findAll().stream()
           .map(this::convertToModel)
           .collect(Collectors.toList());
   }
   @Override
   public HealthCalculationModel getCalculationById(Long id, Long patientId) {
       HealthCalculationEntityId entityId = new HealthCalculationEntityId();
       entityId.setId(id);
       entityId.setPatient(patientId);
       return healthCalculationRepository.findById(entityId)
           .map(this::convertToModel)
           .orElseThrow(() -> new RuntimeException("Calculation not found"));
   }
   @Override
   public List<HealthCalculationModel> getCalculationsByPatientId(Long patientId) {
       return healthCalculationRepository.findByPatientId(patientId).stream()
           .map(this::convertToModel)
           .collect(Collectors.toList());
   }
   @Override
   public HealthCalculationModel createCalculation(HealthCalculationModel model) {
       HealthCalculationEntity entity = new HealthCalculationEntity();
       entity.setDuration(model.getDuration());
       entity.setCurrentPulse(model.getCurrentPulse());
       entity.setMaxPulse(model.getMaxPulse());
       entity.setCalories(model.getCalories());
       entity.setPatient(patientRepository.findById(model.getPatientId())
           .orElseThrow(() -> new RuntimeException("Patient not found")));
       return convertToModel(healthCalculationRepository.save(entity));
   }
   @Override
   public void deleteCalculation(Long id, Long patientId) {
       HealthCalculationEntityId entityId = new HealthCalculationEntityId();
       entityId.setId(id);
       entityId.setPatient(patientId);
       healthCalculationRepository.deleteById(entityId);
   }
   private HealthCalculationModel convertToModel(HealthCalculationEntity entity) {
       HealthCalculationModel model = new HealthCalculationModel();
       model.setId(entity.getId());
       model.setDuration(entity.getDuration());
       model.setCurrentPulse(entity.getCurrentPulse());
       model.setMaxPulse(entity.getMaxPulse());
       model.setCalories(entity.getCalories());
       model.setPatientId(entity.getPatient().getId());
       return model;
   }
}

