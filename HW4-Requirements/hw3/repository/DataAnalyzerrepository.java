package com.DataAnalyzer.hw3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.DataAnalyzer.hw3.models.HealthCalculation;

/**
 * Repository class for managing {@link HealthCalculation} data.
 * Handles data loading from a CSV file, as well as data retrieval, addition, updating, and deletion.
 * This class acts as a data access layer for managing health calculation records.
 */
@Repository
public class DataAnalyzerrepository {
    private static final String FILE_PATH = "DataSource/Data_file.csv";
    private static List<HealthCalculation> healthCalculations = new ArrayList<>();

    /**
     * Static block to load data from the file when the class is first initialized.
     * This ensures that all data is loaded into memory for future operations.
     */
    static {
        loadDataFromFile();
    }

    /**
     * Loads the health calculation data from a CSV file.
     * Each line in the file represents a {@link HealthCalculation} object in CSV format.
     */
    private static void loadDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                healthCalculations.add(HealthCalculation.fromcsvString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current list of {@link HealthCalculation} objects to the CSV file.
     *
     * @return {@code true} if data was saved successfully, {@code false} if an error occurred during saving
     */
    private boolean saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (HealthCalculation hc : healthCalculations) {
                bw.write(hc.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds a new {@link HealthCalculation} to the list.
     * This method checks for duplicates before adding the new record.
     *
     * @param healthCalculation the {@link HealthCalculation} to be added
     * @return {@code true} if the record was added successfully, {@code false} if a duplicate exists
     */
    public boolean addHealthCalculation(HealthCalculation healthCalculation) {
        for (HealthCalculation hc : healthCalculations) {
            if (hc.equals(healthCalculation)) {
                return false;
            }
        }
        healthCalculations.add(healthCalculation);
        return saveDataToFile();
    }

    /**
     * Updates an existing {@link HealthCalculation} in the list.
     * The method searches for the existing record by comparing its duration, current pulse, and max pulse.
     *
     * @param healthCalculation the updated {@link HealthCalculation} object
     * @return {@code true} if the record was updated successfully, {@code false} if the record was not found
     */
    public boolean updateHealthCalculation(HealthCalculation healthCalculation) {
        for (int i = 0; i < healthCalculations.size(); i++) {
            if (healthCalculations.get(i).equals(healthCalculation)) {
                healthCalculations.set(i, healthCalculation);
                return saveDataToFile();
            }
        }
        return false;
    }

    /**
     * Deletes an existing {@link HealthCalculation} from the list.
     * The method searches for the record to delete by comparing its duration, current pulse, and max pulse.
     *
     * @param healthCalculation the {@link HealthCalculation} object to delete
     * @return {@code true} if the record was deleted successfully, {@code false} if the record was not found
     */
    public boolean deleteHealthCalculation(HealthCalculation healthCalculation) {
        if (healthCalculations.removeIf(hc -> hc.equals(healthCalculation))) {
            return saveDataToFile();
        }
        return false;
    }

    /**
     * Retrieves all the {@link HealthCalculation} records from the in-memory list.
     *
     * @return a list of {@link HealthCalculation} objects
     */
    public List<HealthCalculation> getAllHealthCalculations() {
        return healthCalculations;
    }
}
