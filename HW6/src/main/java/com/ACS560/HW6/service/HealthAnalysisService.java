package com.ACS560.HW6.service;

public interface HealthAnalysisService {
    /**
     * Calculate average calories burnt for a patient
     * @param patientId - the patient ID
     * @return average calories burnt
     */
    double calculateAverageCalories(Long patientId);
    
    /**
     * Calculate average calories for duration range
     * @param patientId - the patient ID
     * @param minDuration - minimum duration
     * @param maxDuration - maximum duration
     * @return average calories burnt within duration range
     */
    double calculateAverageCaloriesForDurationRange(Long patientId, int minDuration, int maxDuration);
    
    /**
     * Calculate average pulse rate for a patient
     * @param patientId - the patient ID
     * @return average pulse rate
     */
    double calculateAveragePulseRate(Long patientId);
    
    /**
     * Calculate max pulse rate average for a patient
     * @param patientId - the patient ID
     * @return average maximum pulse rate
     */
    double calculateAverageMaxPulseRate(Long patientId);
    
    /**
     * Calculate total calories burnt for a patient
     * @param patientId - the patient ID
     * @return total calories burnt
     */
    double calculateTotalCalories(Long patientId);
}	
