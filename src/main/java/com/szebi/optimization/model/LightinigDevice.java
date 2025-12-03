package com.szebi.optimization.model;

import jakarta.persistence.Entity;

@Entity
public class LightinigDevice extends Device {
    private int brightness;

    public int getBrightnessLevel() {
        return brightness;
    }

    public void setBrightnessLevel(int brightnessL) {
        this.brightness = brightnessL;
    }

    public LightinigDevice() {
        super();
    }
}
