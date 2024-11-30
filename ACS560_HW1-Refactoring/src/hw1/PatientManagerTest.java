package hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PatientManagerTest {
    @Test
    public void testLoadPatients() {
        String fileName = "Patient_file.csv";
        List<HealthCalculationRefactored> patients = PatientManager.loadPatients(fileName);

        assertEquals(3, patients.size(), "Should load 3 patients from the file.");
        assertEquals(30, patients.get(0).duration, "First patient's duration should be 30.");
        assertEquals(200.0, patients.get(0).caloriesBurned, 0.01, "First patient's calories burned should be 200.");
    }
}
