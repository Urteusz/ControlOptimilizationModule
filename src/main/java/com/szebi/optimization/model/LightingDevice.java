package com.szebi.optimization.model;

import jakarta.persistence.Entity;

@Entity
public class LightingDevice extends Device {
    private int brightness;

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightnessL) {
        this.brightness = brightnessL;
    }

    public LightingDevice(String name, Place place, double powerConsumption, int brightness) {
        super(name, place, powerConsumption);
        this.brightness = brightness;
    }

    public LightingDevice() {
        super();
    }
}
