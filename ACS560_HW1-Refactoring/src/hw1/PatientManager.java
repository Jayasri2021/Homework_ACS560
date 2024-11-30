package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManager {
    public static List<HealthCalculationRefactored> loadPatients(String fileName) {
        List<HealthCalculationRefactored> patients = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int duration = Integer.parseInt(data[0]);
                int currentPulse = Integer.parseInt(data[1]);
                int maxPulse = Integer.parseInt(data[2]);
                double calories = Double.parseDouble(data[3]);
                patients.add(new HealthCalculationRefactored(duration, currentPulse, maxPulse, calories));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
        return patients;
    }
}
