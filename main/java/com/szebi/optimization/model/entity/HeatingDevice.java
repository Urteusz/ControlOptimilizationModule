package com.szebi.optimization.model.entity;

import jakarta.persistence.Entity;

@Entity
public class HeatingDevice extends Device {
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public HeatingDevice() {
        super();
    }

    public HeatingDevice(String name, Place place, double powerConsumption, double temperature) {
        super(name, place, powerConsumption);
        this.temperature = temperature;
    }
}
