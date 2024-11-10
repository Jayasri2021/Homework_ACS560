package com.ACS560.HW6.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HealthCalculationEntity> healthCalculations = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Default constructor
    public PatientEntity() {
    }

    // Constructor with fields
    public PatientEntity(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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

    public List<HealthCalculationEntity> getHealthCalculations() {
        return healthCalculations;
    }

    public void setHealthCalculations(List<HealthCalculationEntity> healthCalculations) {
        this.healthCalculations = healthCalculations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientEntity)) return false;
        PatientEntity that = (PatientEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

