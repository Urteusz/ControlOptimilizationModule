package com.szebi.optimization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class AdministratorPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prefferedMinTemperature;
    private double prefferedMaxTemperature;
    private Time timeOpen;
    private Time timeClose;
    private double maxEnergyUsage;
    private int priorityComfort;

    public AdministratorPreferences(double prefferedMinTemperature, double prefferedMaxTemperature, Time timeOpen, Time timeClose, double maxEnergyUsage, int priorityComfort) {
        this.prefferedMinTemperature = prefferedMinTemperature;
        this.prefferedMaxTemperature = prefferedMaxTemperature;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.maxEnergyUsage = maxEnergyUsage;
        this.priorityComfort = priorityComfort;
    }

    public AdministratorPreferences() {
    }

    public double getPrefferedMinTemperature() {
        return prefferedMinTemperature;
    }

    public void setPrefferedMinTemperature(double prefferedMinTemperature) {
        this.prefferedMinTemperature = prefferedMinTemperature;
    }

    public double getPrefferedMaxTemperature() {
        return prefferedMaxTemperature;
    }

    public void setPrefferedMaxTemperature(double prefferedMaxTemperature) {
        this.prefferedMaxTemperature = prefferedMaxTemperature;
    }

    public Time getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Time timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Time getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Time timeClose) {
        this.timeClose = timeClose;
    }

    public double getMaxEnergyUsage() {
        return maxEnergyUsage;
    }

    public void setMaxEnergyUsage(double maxEnergyUsage) {
        this.maxEnergyUsage = maxEnergyUsage;
    }

    public int getPriorityComfort() {
        return priorityComfort;
    }

    public void setPriorityComfort(int priorityComfort) {
        this.priorityComfort = priorityComfort;
    }
}
