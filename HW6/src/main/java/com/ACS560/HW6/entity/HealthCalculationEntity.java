package com.ACS560.HW6.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_calculations")
@IdClass(HealthCalculationEntityId.class)
public class HealthCalculationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer duration;
    private Integer currentPulse;
    private Integer maxPulse;
    private Integer calories;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

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

    public PatientEntity getPatient() {
        return patient;
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

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}