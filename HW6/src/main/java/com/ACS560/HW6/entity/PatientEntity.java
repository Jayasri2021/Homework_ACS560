package com.ACS560.HW6.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patients")
public class PatientEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthCalculationEntity> healthCalculations = new ArrayList<>();

    // Default Constructor
    public PatientEntity() {
    }

    // Constructor with fields
    public PatientEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<HealthCalculationEntity> getHealthCalculations() {
        return healthCalculations;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHealthCalculations(List<HealthCalculationEntity> healthCalculations) {
        this.healthCalculations = healthCalculations;
    }

    // Helper method to add health calculation
    public void addHealthCalculation(HealthCalculationEntity healthCalculation) {
        healthCalculations.add(healthCalculation);
        healthCalculation.setPatient(this);
    }

    // Helper method to remove health calculation
    public void removeHealthCalculation(HealthCalculationEntity healthCalculation) {
        healthCalculations.remove(healthCalculation);
        healthCalculation.setPatient(null);
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity patient = (PatientEntity) o;
        return Objects.equals(id, patient.id) &&
               Objects.equals(name, patient.name) &&
               Objects.equals(address, patient.address);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    // toString method
    @Override
    public String toString() {
        return "PatientEntity{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}