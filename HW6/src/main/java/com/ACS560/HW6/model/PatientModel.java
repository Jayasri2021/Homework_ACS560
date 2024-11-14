package com.ACS560.HW6.model;

import java.util.ArrayList;
import java.util.List;

public class PatientModel {
    private Long id;
    private String name;
    private String address;
    private List<HealthCalculationModel> healthCalculations = new ArrayList<>();

    // Default constructor
    public PatientModel() {}

    // Constructor with fields
    public PatientModel(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HealthCalculationModel> getHealthCalculations() {
        return healthCalculations;
    }

    public void setHealthCalculations(List<HealthCalculationModel> healthCalculations) {
        this.healthCalculations = healthCalculations;
    }
}