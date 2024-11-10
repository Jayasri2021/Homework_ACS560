package com.ACS560.HW6.entity;

import java.io.Serializable;
import java.util.Objects;

public class HealthCalculationEntityId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long patientId;

    // Default constructor
    public HealthCalculationEntityId() {
    }

    // Constructor with fields
    public HealthCalculationEntityId(Long id, Long patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCalculationEntityId that = (HealthCalculationEntityId) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId);
    }

    @Override
    public String toString() {
        return "HealthCalculationEntityId{" +
                "id=" + id +
                ", patientId=" + patientId +
                '}';
    }
}