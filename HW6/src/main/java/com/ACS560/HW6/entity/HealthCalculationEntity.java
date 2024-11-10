package com.ACS560.HW6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "health_calculations")
@IdClass(HealthCalculationEntityId.class)
public class HealthCalculationEntity {
    @Id
    private Long id;

    @Id
    @Column(name = "patient_id")
    private Long patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer duration;

    @NotNull
    @Min(30)
    @Max(220)
    @Column(name = "current_pulse", nullable = false)
    private Integer currentPulse;

    @NotNull
    @Min(30)
    @Max(220)
    @Column(name = "max_pulse", nullable = false)
    private Integer maxPulse;

    @NotNull
    @Min(0)
    @Column(name = "calories_burnt", nullable = false)
    private Double caloriesBurnt;

    // Default constructor
    public HealthCalculationEntity() {
    }

    // Constructor with fields
    public HealthCalculationEntity(Long id, Long patientId, Integer duration, 
                                 Integer currentPulse, Integer maxPulse, Double caloriesBurnt) {
        this.id = id;
        this.patientId = patientId;
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.caloriesBurnt = caloriesBurnt;
    }

    @PrePersist
    @PreUpdate
    private void validatePulseRates() {
        if (currentPulse > maxPulse) {
            throw new IllegalStateException("Current pulse cannot be greater than max pulse");
        }
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

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
        if (patient != null) {
            this.patientId = patient.getId();
        }
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

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthCalculationEntity)) return false;
        HealthCalculationEntity that = (HealthCalculationEntity) o;
        return id != null && patientId != null && 
               id.equals(that.getId()) && 
               patientId.equals(that.getPatientId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}