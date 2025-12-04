package com.szebi.optimization.model;

import jakarta.persistence.Entity;

@Entity
public class VentilationDevice extends Device {
    private int airFlowRate;

    public int getAirFlowRate() {
        return airFlowRate;
    }

    public void setAirFlowRate(int airFlowRate) {
        this.airFlowRate = airFlowRate;
    }

    public VentilationDevice() {
        super();
    }
}
