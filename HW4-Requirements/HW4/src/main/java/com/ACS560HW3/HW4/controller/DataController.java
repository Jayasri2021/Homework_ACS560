package com.ACS560HW3.HW4.controller;


import com.ACS560HW3.HW4.models.HealthCalculation;
import com.ACS560HW3.HW4.services.DataAnalyzerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Controller for handling CRUD operations and search filters for HealthCalculation data.
*/
@RestController
@RequestMapping("/api/data/getAll")
public class DataController {

  private final DataAnalyzerService dataAnalyzerService;

  @Autowired
  public DataController(DataAnalyzerService dataAnalyzerService) {
      this.dataAnalyzerService = dataAnalyzerService;
  }

  /**
   * Gets all health calculations.
   *
   * @return List of all HealthCalculation objects
   */
  @GetMapping
  public List<HealthCalculation> getAllHealthCalculations() {
      return dataAnalyzerService.getAllHealthCalculations();
  }

   /**
   * Gets health calculations filtered by duration.
   *
   * @param duration The duration to filter by
   * @return List of HealthCalculation objects with the specified duration
   */
  @GetMapping("/duration/{duration}/heart-rate/{heartRate}")
  public List<HealthCalculation> getHealthCalculationsByDurationAndHeartRate(
          @PathVariable int duration,
          @PathVariable int heartRate) {
      return dataAnalyzerService.getAllHealthCalculations().stream()
              .filter(calc -> calc.getDuration() == duration && calc.getCurrentPulse() == heartRate)
              .toList();
  }
}