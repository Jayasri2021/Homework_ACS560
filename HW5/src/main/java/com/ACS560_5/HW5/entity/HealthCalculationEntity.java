package com.ACS560_5.HW5.entity;

import com.ACS560_5.HW5.models.HealthCalculation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "PatientData")
public class HealthCalculationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int duration;
    private int currentPulse;
    private int maxPulse;
    private double calories;

    // Default constructor
    public HealthCalculationEntity() {}

    // Constructor using HealthCalculation
    public HealthCalculationEntity(HealthCalculation calculation) {
        this.duration = calculation.getDuration();
        this.currentPulse = calculation.getCurrentPulse();
        this.maxPulse = calculation.getMaxPulse();
        this.calories = calculation.getCalories();
    }

    // Constructor with all fields
    public HealthCalculationEntity(Long id, int duration, int currentPulse, int maxPulse, double calories) {
        this.id = id;
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.calories = calories;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCurrentPulse() {
        return currentPulse;
    }

    public void setCurrentPulse(int currentPulse) {
        this.currentPulse = currentPulse;
    }

    public int getMaxPulse() {
        return maxPulse;
    }

    public void setMaxPulse(int maxPulse) {
        this.maxPulse = maxPulse;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "HealthCalculationEntity{" +
                "id=" + id +
                ", duration=" + duration +
                ", currentPulse=" + currentPulse +
                ", maxPulse=" + maxPulse +
                ", calories=" + calories +
                '}';
    }
}
