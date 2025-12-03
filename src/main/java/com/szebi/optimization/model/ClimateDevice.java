package com.szebi.optimization.model;

import jakarta.persistence.Entity;

@Entity
public class ClimateDevice extends Device {
    private double temperatureSetting;
    private int airFlowRate;

    public double getTemperatureSetting() {
        return temperatureSetting;
    }

    public void setTemperatureSetting(double temperatureSetting) {
        this.temperatureSetting = temperatureSetting;
    }

    public int getAirFlowRate() {
        return airFlowRate;
    }

    public void setAirFlowRate(int airFlowRate) {
        this.airFlowRate = airFlowRate;
    }

    public ClimateDevice() {
        super();
    }
}
