package com.ACS560.HW6.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;

public class HealthCalculationRequest {
    
    private Long id;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    @NotNull(message = "Current pulse is required")
    @Positive(message = "Current pulse must be positive")
    private Integer currentPulse;

    @NotNull(message = "Max pulse is required")
    @Positive(message = "Max pulse must be positive")
    private Integer maxPulse;

    @NotNull(message = "Calories burnt is required")
    @Min(value = 0, message = "Calories burnt cannot be negative")
    private Double caloriesBurnt;

    // Default constructor
    public HealthCalculationRequest() {
    }

    // Constructor with all fields
    public HealthCalculationRequest(Long id, Long patientId, Integer duration, 
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

    @Override
    public String toString() {
        return "HealthCalculationRequest{" +
               "id=" + id +
               ", patientId=" + patientId +
               ", duration=" + duration +
               ", currentPulse=" + currentPulse +
               ", maxPulse=" + maxPulse +
               ", caloriesBurnt=" + caloriesBurnt +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthCalculationRequest that = (HealthCalculationRequest) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!patientId.equals(that.patientId)) return false;
        if (!duration.equals(that.duration)) return false;
        if (!currentPulse.equals(that.currentPulse)) return false;
        if (!maxPulse.equals(that.maxPulse)) return false;
        return caloriesBurnt.equals(that.caloriesBurnt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + patientId.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + currentPulse.hashCode();
        result = 31 * result + maxPulse.hashCode();
        result = 31 * result + caloriesBurnt.hashCode();
        return result;
    }
}