package com.DataAnalyzer.hw2.models;

/**
 * Represents a health calculation with duration, current pulse, max pulse, and calories burnt in the session.
 */
public class HealthCalculation 
{
    private int duration;
    private int currentPulse;
    private int maxPulse;
    private double calories;

    /**
     * New HealthCalculation instance.
     *
     * @param duration     The duration of the exercise in minutes
     * @param currentPulse The current pulse rate
     * @param maxPulse     The maximum pulse rate
     * @param calories     The number of calories burned
     */
    public HealthCalculation(int duration, int currentPulse, int maxPulse, double calories) {
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.calories = calories;
    }

    /**
     * @return The duration of the activity in minutes
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return The current pulse rate
     */
    public int getCurrentPulse() {
        return currentPulse;
    }

    /**
     * @return The maximum pulse rate
     */
    public int getMaxPulse() {
        return maxPulse;
    }

    /**
     * @return The number of calories burned
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the duration of the activity.
     *
     * @param duration The new duration in minutes
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets the current pulse rate.
     *
     * @param currentPulse The new current pulse rate
     */
    public void setCurrentPulse(int currentPulse) {
        this.currentPulse = currentPulse;
    }

    /**
     * Sets the maximum pulse rate.
     *
     * @param maxPulse The new maximum pulse rate
     */
    public void setMaxPulse(int maxPulse) {
        this.maxPulse = maxPulse;
    }

    /**
     * Sets the number of calories burned.
     *
     * @param calories The new number of calories burned
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * @return A string representation of the HealthCalculation
     */
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