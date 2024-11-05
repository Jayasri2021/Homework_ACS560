package com.ACS560.HW7.entities;

import lombok.Data;
import java.io.Serializable;

@Data
public class HealthCalculationEntityId implements Serializable {
    private Long id;
    private Long patient; // This matches with patient_id in HealthCalculationEntity

    // Constructors
    public HealthCalculationEntityId() {}

    public HealthCalculationEntityId(Long id, Long patient) {
        this.id = id;
        this.patient = patient;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getPatient() {
        return patient;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    // Required for composite key class
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthCalculationEntityId that = (HealthCalculationEntityId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return patient != null ? patient.equals(that.patient) : that.patient == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (patient != null ? patient.hashCode() : 0);
        return result;
    }
}