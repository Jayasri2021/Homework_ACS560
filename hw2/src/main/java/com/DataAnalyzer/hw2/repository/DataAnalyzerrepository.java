package com.DataAnalyzer.hw2.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.DataAnalyzer.hw2.models.HealthCalculation;
import org.springframework.stereotype.Repository;

/**
 * Repository class for managing HealthCalculation data.
 * Handles data loading from file and provides methods for data retrieval.
 */
@Repository
public class DataAnalyzerrepository 
{
    private static List<HealthCalculation> healthCalculations;
    private static final String FILE_PATH = "DataSource/Patient_file.csv";
    
    static 
    {
        healthCalculations = new ArrayList<>();
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
            br.readLine(); 
            while ((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");
                healthCalculations.add(new HealthCalculation(
                        Integer.parseInt(values[0]),  
                        Integer.parseInt(values[1]),  
                        Integer.parseInt(values[2]),
                        Double.parseDouble(values[3]) 
                ));
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all HealthCalculation records.
     *
     * @return List of all HealthCalculation objects
     */
    public List<HealthCalculation> getAllHealthCalculations()
    {
        return healthCalculations;
    }
}