package com.szebi.optimization.model.entity;

import jakarta.persistence.Entity;

@Entity
public class ClimateDevice extends Device {
    private double temperature;
    private int airFlowRate;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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
