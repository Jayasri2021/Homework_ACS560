package hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HealthCalculationTest {

    @Test
    public void testConstructorAndFields() {
        HealthCalculationRefactored hc = new HealthCalculationRefactored(30, 80, 150, 200.0);

        assertEquals(30, hc.duration, "Duration should be set correctly.");
        assertEquals(80, hc.currentPulse, "Current pulse should be set correctly.");
        assertEquals(150, hc.maxPulse, "Max pulse should be set correctly.");
        assertEquals(200.0, hc.caloriesBurned, "Calories burned should be set correctly.");
    }

    @Test
    public void testPulsePercentageCalculation() {
        HealthCalculationRefactored hc = new HealthCalculationRefactored(30, 80, 150, 200.0);

        double expectedPercentage = (80.0 / 150.0) * 100;
        double actualPercentage = ((double) hc.currentPulse / hc.maxPulse) * 100;

        assertEquals(expectedPercentage, actualPercentage, 0.01, "Pulse percentage calculation is incorrect.");
    }

    @Test
    public void testCaloriesPerMinuteCalculation() {
        HealthCalculationRefactored hc = new HealthCalculationRefactored(30, 80, 150, 200.0);

        double expectedCaloriesPerMinute = 200.0 / 30.0;
        double actualCaloriesPerMinute = hc.caloriesBurned / hc.duration;

        assertEquals(expectedCaloriesPerMinute, actualCaloriesPerMinute, 0.01, "Calories per minute calculation is incorrect.");
    }

    @Test
    public void testAverageCaloriesAcrossPatients() {
        HealthCalculationRefactored hc1 = new HealthCalculationRefactored(30, 80, 150, 200.0);
        HealthCalculationRefactored hc2 = new HealthCalculationRefactored(45, 85, 160, 300.0);
        HealthCalculationRefactored hc3 = new HealthCalculationRefactored(60, 90, 170, 400.0);

        List<HealthCalculationRefactored> patients = List.of(hc1, hc2, hc3);
        double expectedAverage = (hc1.caloriesBurned / hc1.duration + hc2.caloriesBurned / hc2.duration + hc3.caloriesBurned / hc3.duration) / 3;

        double actualAverage = patients.stream()
                .mapToDouble(hc -> hc.caloriesBurned / hc.duration)
                .average()
                .orElse(0.0);

        assertEquals(expectedAverage, actualAverage, 0.01, "Average calories across patients calculation is incorrect.");
    }
}
