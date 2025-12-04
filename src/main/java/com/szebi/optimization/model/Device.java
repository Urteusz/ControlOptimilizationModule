package com.szebi.optimization.model;

import jakarta.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "device_place_id")
    private Place devicePlace;
    private statusState status;
    private double powerConsumption;


    public Device() {}

    public Device(String name, Place place, double powerConsumption) {
        this.name = name;
        this.devicePlace = place;
        this.status = statusState.OFF;
        this.powerConsumption = powerConsumption;
    }

    public Place getDevicePlace() {
        return devicePlace;
    }

    public void setDevicePlace(Place devicePlace) {
        this.devicePlace = devicePlace;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public statusState getStatus() {
        return status;
    }

    public void setStatus(statusState status) {
        this.status = status;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
}
