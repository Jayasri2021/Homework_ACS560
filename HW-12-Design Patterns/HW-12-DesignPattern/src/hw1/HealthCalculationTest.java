package hw1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import hw1.strategies.*;
import org.junit.jupiter.api.BeforeEach;  // instead of @Before
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;   // instead of @After
import static org.junit.jupiter.api.Assertions.*;

public class HealthCalculationTest {
    private static final String TEST_DATA = 
        "60,110,130,409.1\n" +
        "45,109,175,282.4\n" +
        "60,117,145,479.0";
    
    private File testFile;
    private List<HealthCalculation> testDataList;

    @BeforeEach
    public void setUp() throws IOException {
        // Create test file
        testFile = new File("src/hw1/Patient_file.csv");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(TEST_DATA);
        }
        
        // Load test data
        testDataList = HealthCalculation.readData();
    }

    @Test
    public void testDurationSumStrategy() {
        HealthCalculation.HealthCalculator calculator = new HealthCalculation.HealthCalculator();
        calculator.setStrategy(new DurationSumStrategy());
        double sum = calculator.executeCalculation(testDataList);
        assertEquals(165.0, sum, 0.01);
    }

    @Test
    public void testCaloriesMeanStrategy() {
        HealthCalculation.HealthCalculator calculator = new HealthCalculation.HealthCalculator();
        calculator.setStrategy(new CaloriesMeanStrategy());
        double mean = calculator.executeCalculation(testDataList);
        assertEquals(390.17, mean, 0.01);
    }

    @Test
    public void testMaxCaloriesForDurationStrategy() {
        HealthCalculation.HealthCalculator calculator = new HealthCalculation.HealthCalculator();
        calculator.setStrategy(new MaxCaloriesForDurationStrategy(60));
        double max = calculator.executeCalculation(testDataList);
        assertEquals(479.0, max, 0.01);
    }

    @AfterEach
    public void cleanup() {
        if (testFile != null && testFile.exists()) {
            testFile.delete();
        }
    }
}