package com.ACS560.HW6.request;

import java.io.Serializable;
import java.util.Objects;

public class PatientRequest implements Serializable {
    private String name;
    private String address;

    // Default Constructor
    public PatientRequest() {
    }

    // Constructor with fields
    public PatientRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Setters
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
        PatientRequest that = (PatientRequest) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(address, that.address);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    // toString method
    @Override
    public String toString() {
        return "PatientRequest{" +
               "name='" + name + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}