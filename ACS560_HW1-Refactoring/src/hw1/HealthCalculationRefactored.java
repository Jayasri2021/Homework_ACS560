package hw1;

import java.util.List;
import java.util.OptionalDouble;

public class HealthCalculationRefactored {
    int duration;
    int currentPulse;
    int maxPulse;
    double caloriesBurned;

    public HealthCalculationRefactored(int duration, int currentPulse, int maxPulse, double caloriesBurned) {
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.caloriesBurned = caloriesBurned;
    }

    public double calculatePulsePercentage() {
        return ((double) currentPulse / maxPulse) * 100;
    }

    public double calculateCaloriesPerMinute() {
        return caloriesBurned / duration;
    }

    public static OptionalDouble calculateAverage(List<HealthCalculationRefactored> records) {
        return records.stream()
                .mapToDouble(HealthCalculationRefactored::calculateCaloriesPerMinute)
                .average();
    }

    @Override
    public String toString() {
        return "Duration: " + duration +
                ", Current Pulse: " + currentPulse +
                ", Max Pulse: " + maxPulse +
                ", Calories Burned: " + caloriesBurned;
    }
}

