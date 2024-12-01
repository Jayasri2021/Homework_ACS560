package hw1.strategies;

import hw1.CalculationStrategy;
import hw1.HealthCalculation;
import java.util.List;

public class MaxCaloriesForDurationStrategy implements CalculationStrategy {
    private final int targetDuration;
    
    public MaxCaloriesForDurationStrategy(int targetDuration) {
        this.targetDuration = targetDuration;
    }
    
    @Override
    public double calculate(List<HealthCalculation> data) {
        return data.stream()
                  .filter(h -> h.getDuration() == targetDuration)
                  .mapToDouble(HealthCalculation::getCalories)
                  .max()
                  .orElse(Double.MIN_VALUE);
    }
}