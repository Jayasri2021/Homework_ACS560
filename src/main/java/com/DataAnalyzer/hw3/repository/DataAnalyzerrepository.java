package com.DataAnalyzer.hw3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.DataAnalyzer.hw3.models.HealthCalculation;

/**
 * Repository class for managing HealthCalculation data.
 * Handles data loading from file and provides methods for data retrieval.
 */
@Repository

public class DataAnalyzerrepository 
{
    private static final String FILE_PATH = "DataSource/Data_file.csv";

    private static List<HealthCalculation> healthCalculations = new ArrayList<>();
    
    // Static to load data at the start from CSV
    
    static 
    {
        loadDataFromFile();        
    }

    /**
     * Loads data from the CSV file into the healthCalculations list.
     */
    private static void loadDataFromFile() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                healthCalculations.add(HealthCalculation.fromcsvString(line));
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
/**
 * Saves the current healthCalculations list back to the CSV file.
 * For the add operation, this appends data; for update and delete, it overwrites.
 * 
 * @return true it data was saved.
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
     * If HeathCalculation does not exist, it adds it to the new data structure.
     * 
     * @param healthCalculation the added HealthCalculation
     * @return true if added.
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
     * Updates the existing HealthCalculation in the data structure.
     * 
     * @param healthCalculation The HealthCalculation to update.
     * @return true if updated.
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
    * Deletes an existing HealthCalculation from the data structure.
    *
    * @param healthCalculation The HealthCalculation to be deleted.
    * @return true if deleted successfully, false if it does not exist.
    */
    public boolean deleteHealthCalculation(HealthCalculation healthCalculation) {
        if (healthCalculations.removeIf(hc -> hc.equals(healthCalculation))) {
            return saveDataToFile();
        }
        return false;
    }
    /**
     * Retrieves all HealthCalculation records.
     *
     * @return A list of all HealthCalculation objects.
     */
    
    public List<HealthCalculation> getAllHealthCalculations() 
    {
        return healthCalculations;
    }
}