package hw1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "Patient_file.csv";
        List<HealthCalculationRefactored> patients = PatientManager.loadPatients(fileName);

        System.out.println("Loaded Patients:");
        for (HealthCalculationRefactored patient : patients) {
            System.out.println(patient);
        }

        System.out.println("\nAverage Calories Burned per Minute:");
        double averageCalories = HealthCalculationRefactored.calculateAverage(patients).orElse(0.0);
        System.out.println(averageCalories);
    }
}
