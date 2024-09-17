package com.DataAnalyzer.hw3.services.impl;

import com.DataAnalyzer.hw3.models.HealthCalculation;
import com.DataAnalyzer.hw3.repository.DataAnalyzerrepository;
import com.DataAnalyzer.hw3.services.DataAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the DataAnalyzerService interface.
 */
@Service
public class DataAnalyzerServiceImpl implements DataAnalyzerService {

    private final DataAnalyzerrepository dataAnalyzerRepository;

    /**
     * Constructor to inject DataAnalyzerRepository.
     * 
     * @param dataAnalyzerRepository is the instance to inject
     */
    
    @Autowired
    public DataAnalyzerServiceImpl(DataAnalyzerrepository dataAnalyzerRepository) {
        this.dataAnalyzerRepository = dataAnalyzerRepository;
    }

    /**
    * Retrieves all health calculations from the repository.
    *
    * @return List of all HealthCalculation objects.
    */
    
    @Override
    public List<HealthCalculation> getAllHealthCalculations() {
        return dataAnalyzerRepository.getAllHealthCalculations();
    }

    /**
     * Adds a new health calculation and saves it to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be added.
     * @return true if added, false otherwise.
     */
    
    @Override
    public boolean addHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.addHealthCalculation(healthCalculation);
    }
    
    /**
     * Updates an existing health calculation and saves it to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be updated.
     * @return true if updated successfully, false otherwise.
     */
    @Override
    public boolean updateHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.updateHealthCalculation(healthCalculation);
    }

    /**
     * Deletes an existing health calculation and saves the changes to the CSV file.
     *
     * @param healthCalculation The HealthCalculation to be deleted.
     * @return true if deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteHealthCalculation(HealthCalculation healthCalculation) {
        return dataAnalyzerRepository.deleteHealthCalculation(healthCalculation);
    }

	@Override
	public int getDataSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAverageCalories() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxCaloriesBurned() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxCaloriesBurntByDuration(int duration) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxCaloriesBurntByDurationAndHeartRate(int duration, int heartRate) {
		// TODO Auto-generated method stub
		return 0;
	}
    
//    /*
//     * 
//     */
//    @Override
//    public double getAverageCalories() {
//        List<HealthCalculation> calculations = getAllHealthCalculations();
//        if (calculations.isEmpty()) {
//            return 0;
//        }
//        double totalCalories = calculations.stream()
//                .mapToDouble(HealthCalculation::getCalories)
//                .sum();
//        return totalCalories / calculations.size();
//    }
//
//    @Override
//    public double getMaxCaloriesBurned() {
//        List<HealthCalculation> calculations = getAllHealthCalculations();
//        return calculations.stream()
//                .mapToDouble(HealthCalculation::getCalories)
//                .max()
//                .orElse(0);
//    }
//
//    @Override
//    public double getMaxCaloriesBurntByDuration(int duration) {
//        List<HealthCalculation> calculations = getAllHealthCalculations();
//        return calculations.stream()
//                .filter(calc -> calc.getDuration() == duration)
//                .mapToDouble(HealthCalculation::getCalories)
//                .max()
//                .orElse(0);
//    }
//
//    @Override
//    public double getMaxCaloriesBurntByDurationAndHeartRate(int duration, int heartRate) {
//        List<HealthCalculation> calculations = getAllHealthCalculations();
//        return calculations.stream()
//                .filter(calc -> calc.getDuration() == duration && calc.getCurrentPulse() == heartRate)
//                .mapToDouble(HealthCalculation::getCalories)
//                .max()
//                .orElse(0);
//    }
//
//	@Override
//	public int getDataSize() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}