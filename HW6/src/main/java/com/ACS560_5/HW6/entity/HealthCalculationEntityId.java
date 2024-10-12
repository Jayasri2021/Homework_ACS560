package com.ACS560_5.HW6.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Embeddable
public class HealthCalculationEntityId implements Serializable {

    private Long id;  // This can be part of the composite key if needed

    // Default constructor
    public HealthCalculationEntityId() {}

    // Constructor
    public HealthCalculationEntityId(Long id) {
        this.id = id;
    }

    // Getters
    public Long getId() {
        return id;
    }

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HealthCalculationEntityId)) return false;
        HealthCalculationEntityId that = (HealthCalculationEntityId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "HealthCalculationEntityId{" +
                "id=" + id +
                '}';
    }
}
