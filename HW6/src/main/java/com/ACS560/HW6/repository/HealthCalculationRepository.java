package com.ACS560.HW6.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ACS560.HW6.entity.HealthCalculationEntity;
import com.ACS560.HW6.entity.HealthCalculationEntityId;
import com.ACS560.HW6.model.HealthCalculationModel;

import java.util.List;

public interface HealthCalculationRepository extends CrudRepository<HealthCalculationEntity, HealthCalculationEntityId> {
    
    // Basic finder methods
    List<HealthCalculationEntity> findAllByPatientId(Long patientId);
    List<HealthCalculationEntity> findByDurationBetween(Integer minDuration, Integer maxDuration);
    
    // DTO query methods
    @Query("SELECT new com.ACS560.HW6.model.HealthCalculationModel(h.id, h.patientId, h.duration, " +
           "h.currentPulse, h.maxPulse, h.caloriesBurnt) FROM HealthCalculationEntity h")
    List<HealthCalculationModel> findAllModels();
    
    @Query("SELECT new com.ACS560.HW6.model.HealthCalculationModel(h.id, h.patientId, h.duration, " +
           "h.currentPulse, h.maxPulse, h.caloriesBurnt) FROM HealthCalculationEntity h " +
           "WHERE h.patientId = ?1")
    List<HealthCalculationModel> findModelsByPatientId(Long patientId);
    
    @Query("SELECT new com.ACS560.HW6.model.HealthCalculationModel(h.id, h.patientId, h.duration, " +
           "h.currentPulse, h.maxPulse, h.caloriesBurnt) FROM HealthCalculationEntity h " +
           "WHERE h.duration BETWEEN ?1 AND ?2")
    List<HealthCalculationModel> findModelsByDurationBetween(Integer minDuration, Integer maxDuration);
    
    // Statistical queries
    @Query("SELECT AVG(h.caloriesBurnt) FROM HealthCalculationEntity h WHERE h.patientId = ?1")
    Double calculateAverageCaloriesByPatient(Long patientId);
    
    @Query("SELECT AVG(h.currentPulse) FROM HealthCalculationEntity h WHERE h.patientId = ?1")
    Double calculateAverageCurrentPulseByPatient(Long patientId);
    
    @Query("SELECT AVG(h.maxPulse) FROM HealthCalculationEntity h WHERE h.patientId = ?1")
    Double calculateAverageMaxPulseByPatient(Long patientId);
    
    @Query("SELECT SUM(h.caloriesBurnt) FROM HealthCalculationEntity h WHERE h.patientId = ?1")
    Double calculateTotalCaloriesByPatient(Long patientId);
    
    @Query("SELECT AVG(h.caloriesBurnt) FROM HealthCalculationEntity h " +
           "WHERE h.patientId = ?1 AND h.duration BETWEEN ?2 AND ?3")
    Double calculateAverageCaloriesForDurationRange(Long patientId, Integer minDuration, Integer maxDuration);
}
