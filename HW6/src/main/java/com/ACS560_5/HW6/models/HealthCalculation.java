package com.ACS560_5.HW6.models;

import java.util.Objects;

public class HealthCalculation {
    private int duration;
    private int currentPulse;
    private int maxPulse;
    private double calories;

    // Default constructor
    public HealthCalculation() {}

    // Constructor with all fields
    public HealthCalculation(int duration, int currentPulse, int maxPulse, double calories) {
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.calories = calories;
    }

    // Getters
    public int getDuration() {
        return duration;
    }

    public int getCurrentPulse() {
        return currentPulse;
    }

    public int getMaxPulse() {
        return maxPulse;
    }

    public double getCalories() {
        return calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, currentPulse, maxPulse);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HealthCalculation that = (HealthCalculation) obj;
        return duration == that.duration &&
               currentPulse == that.currentPulse &&
               maxPulse == that.maxPulse &&
               Double.compare(that.calories, calories) == 0;
    }

    @Override
    public String toString() {
        return "HealthCalculation{" +
               "duration=" + duration +
               ", currentPulse=" + currentPulse +
               ", maxPulse=" + maxPulse +
               ", calories=" + calories +
               '}';
    }
}
