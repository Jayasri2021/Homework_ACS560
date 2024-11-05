package com.ACS560.HW7.models;
public class HealthCalculationModel {
   private Long id;
   private Integer duration;
   private Integer currentPulse;
   private Integer maxPulse;
   private Integer calories;
   private Long patientId;
   // Getters
   public Long getId() {
       return id;
   }
   public Integer getDuration() {
       return duration;
   }
   public Integer getCurrentPulse() {
       return currentPulse;
   }
   public Integer getMaxPulse() {
       return maxPulse;
   }
   public Integer getCalories() {
       return calories;
   }
   public Long getPatientId() {
       return patientId;
   }
   // Setters
   public void setId(Long id) {
       this.id = id;
   }
   public void setDuration(Integer duration) {
       this.duration = duration;
   }
   public void setCurrentPulse(Integer currentPulse) {
       this.currentPulse = currentPulse;
   }
   public void setMaxPulse(Integer maxPulse) {
       this.maxPulse = maxPulse;
   }
   public void setCalories(Integer calories) {
       this.calories = calories;
   }
   public void setPatientId(Long patientId) {
       this.patientId = patientId;
   }
}

