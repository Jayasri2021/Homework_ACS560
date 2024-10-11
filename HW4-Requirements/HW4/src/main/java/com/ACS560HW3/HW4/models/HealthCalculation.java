package com.ACS560HW3.HW4.models;

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
     * @param currentPulse The currensz1qt pulse rate
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
     * Gets the duration of the exercise session.
     *
     * @return the duration in minutes
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the exercise session.
     *
     * @param duration the duration to set in minutes
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the current pulse rate during the exercise session.
     *
     * @return the current pulse rate
     */
    public int getCurrentPulse() {
        return currentPulse;
    }

    /**
     * Sets the current pulse rate during the exercise session.
     *
     * @param currentPulse the pulse rate to set
     */
    public void setCurrentPulse(int currentPulse) {
        this.currentPulse = currentPulse;
    }

    /**
     * Gets the maximum pulse rate recorded during the exercise session.
     *
     * @return the maximum pulse rate
     */
    public int getMaxPulse() {
        return maxPulse;
    }

    /**
     * Sets the maximum pulse rate recorded during the exercise session.
     *
     * @param maxPulse the maximum pulse rate to set
     */
    public void setMaxPulse(int maxPulse) {
        this.maxPulse = maxPulse;
    }

    /**
     * Gets the total calories burned during the exercise session.
     *
     * @return the number of calories burned
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the total calories burned during the exercise session.
     *
     * @param calories the number of calories to set
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Converts the {@code HealthCalculation} instance to a CSV string format.
     *
     * @return a CSV representation of the health calculation (duration, current pulse, max pulse, calories)
     */
    @Override
    public String toString() {
        return duration + "," + currentPulse + "," + maxPulse + "," + calories;
    }

    /**
     * Creates a new {@code HealthCalculation} instance from a CSV string.
     * The CSV string should follow the format: duration,currentPulse,maxPulse,calories
     *
     * @param csvString the CSV-formatted string representing the health calculation
     * @return a new {@code HealthCalculation} object parsed from the input string
     * @throws NumberFormatException if the input values cannot be parsed into appropriate data types
     */
    public static HealthCalculation fromcsvString(String csvString) {
        String[] values = csvString.split(",");
        return new HealthCalculation(
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Double.parseDouble(values[3])
        );
    }

    /**
     * Checks if this {@code HealthCalculation} is equal to another based on
     * duration, current pulse, and maximum pulse values.
     *
     * @param other the other {@code HealthCalculation} object to compare to
     * @return {@code true} if both calculations have the same duration, current pulse, and max pulse; {@code false} otherwise
     */
    public boolean equals(HealthCalculation other) {
        return this.duration == other.duration &&
                this.currentPulse == other.currentPulse &&
                this.maxPulse == other.maxPulse;
    }
}