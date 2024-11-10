package com.ACS560.HW6.model;

public class HealthCalculationModel {
    private Long id;
    private Long patientId;
    private Integer duration;
    private Integer currentPulse;
    private Integer maxPulse;
    private Double caloriesBurnt;

    // Default constructor
    public HealthCalculationModel() {}

    // Constructor with fields
    public HealthCalculationModel(Long id, Long patientId, Integer duration, 
                              Integer currentPulse, Integer maxPulse, Double caloriesBurnt) {
        this.id = id;
        this.patientId = patientId;
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.caloriesBurnt = caloriesBurnt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCurrentPulse() {
        return currentPulse;
    }

    public void setCurrentPulse(Integer currentPulse) {
        this.currentPulse = currentPulse;
    }

    public Integer getMaxPulse() {
        return maxPulse;
    }

    public void setMaxPulse(Integer maxPulse) {
        this.maxPulse = maxPulse;
    }

    public Double getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(Double caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }
}