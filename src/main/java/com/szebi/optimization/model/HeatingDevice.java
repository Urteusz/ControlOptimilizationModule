package com.szebi.optimization.model;

import jakarta.persistence.Entity;

@Entity
public class HeatingDevice extends Device {
    private double temperatureSetting;

    public double getTemperatureSetting() {
        return temperatureSetting;
    }

    public void setTemperatureSetting(double temperatureSetting) {
        this.temperatureSetting = temperatureSetting;
    }

    public HeatingDevice() {
        super();
    }
}
