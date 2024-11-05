package com.ACS560.HW7.models;

import java.io.Serializable;
import java.util.Objects;

public class PatientModel implements Serializable {
    private Long id;
    private String name;
    private String address;

    // Default Constructor
    public PatientModel() {
    }

    // Constructor with fields
    public PatientModel(Long id, String name, String address) {
        this.id = id;
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

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientModel that = (PatientModel) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(address, that.address);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    // toString method
    @Override
    public String toString() {
        return "PatientModel{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}