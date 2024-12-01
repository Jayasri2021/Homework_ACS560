package hw1.strategies;

import hw1.CalculationStrategy;
import hw1.HealthCalculation;
import java.util.List;

public class DurationSumStrategy implements CalculationStrategy {
    @Override
    public double calculate(List<HealthCalculation> data) {
        return data.stream()
                  .mapToDouble(HealthCalculation::getDuration)
                  .sum();
    }
}