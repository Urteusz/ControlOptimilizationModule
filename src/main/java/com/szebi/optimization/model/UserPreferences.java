package com.szebi.optimization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private int prefferedTemperature;
    private int prefferedLightining;
    private String currentPlaceName;

    public UserPreferences(int tempScale, int lightScale, String currentPlaceName) {
        this.prefferedTemperature = Math.max(0, Math.min(5, tempScale));
        this.prefferedLightining = Math.max(0, Math.min(5, lightScale));
        this.currentPlaceName = currentPlaceName;
    }

    public UserPreferences() {
    }

    public int getPrefferedTemperature() {
        return prefferedTemperature;
    }

    public void setPrefferedTemperature(int prefferedTemperature) {
        this.prefferedTemperature = prefferedTemperature;
    }

    public int getPrefferedLightining() {
        return prefferedLightining;
    }

    public void setPrefferedLightining(int prefferedLightining) {
        this.prefferedLightining = prefferedLightining;
    }

    public String getCurrentPlaceName() {
        return currentPlaceName;
    }

    public void setCurrentPlaceName(String currentPlaceName) {
        this.currentPlaceName = currentPlaceName;
    }
}
