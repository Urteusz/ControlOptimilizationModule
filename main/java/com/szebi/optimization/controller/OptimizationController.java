package com.szebi.optimization.controller;

import com.szebi.optimization.model.entity.AdministratorPreferences;
import com.szebi.optimization.model.entity.Device;
import com.szebi.optimization.model.entity.UserPreferences;
import com.szebi.optimization.service.AdministratorPreferencesService;
import com.szebi.optimization.service.DeviceControlService;
import com.szebi.optimization.service.OptimizationService;
import com.szebi.optimization.service.UserPreferencesService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/optimization")
public class OptimizationController {

    private final List<Device> devices;
    private boolean overrideAutomation;

    private final OptimizationService optimizationService;
    private final DeviceControlService deviceControlService;
    private final UserPreferencesService userPreferencesService;
    private final AdministratorPreferencesService administratorPreferencesService;

    public OptimizationController(OptimizationService optimizationService,
                                  DeviceControlService deviceControlService,
                                  UserPreferencesService userPreferencesService,
                                  AdministratorPreferencesService administratorPreferencesService) {
        this.devices = new ArrayList<>();
        this.overrideAutomation = false;
        this.optimizationService = optimizationService;
        this.deviceControlService = deviceControlService;
        this.userPreferencesService = userPreferencesService;
        this.administratorPreferencesService = administratorPreferencesService;
    }

    @GetMapping("/devices")
    public List<Device> getDevices() {
        return devices;
    }

    @PostMapping("/devices")
    public void addDevice(@RequestBody Device device) {
        this.devices.add(device);
    }

    public boolean isOverrideAutomation() {
        return overrideAutomation;
    }

    public void setOverrideAutomation(boolean overrideAutomation) {
        this.overrideAutomation = overrideAutomation;
    }

    @GetMapping("/run")
    public void optimizeDevices() {
        if (overrideAutomation) {
            System.out.println("Automatyzacja jest wyłączona (Manual Override).");
            return;
        }

        AdministratorPreferences adminPrefs = administratorPreferencesService.getAdministratorPreferences();
        List<UserPreferences> allUsers = userPreferencesService.getAllUserPreferencesList();

        List<Device> allDevicesFromDb = deviceControlService.getAllDevices();

        Map<String, List<Device>> devicesByPlace = allDevicesFromDb.stream()
                .filter(d -> d.getDevicePlace() != null)
                .collect(Collectors.groupingBy(d -> d.getDevicePlace().getName()));

        if (devicesByPlace.isEmpty()) {
            System.out.println("Nie znaleziono żadnych urządzeń z przypisanym miejscem.");
        }

        devicesByPlace.forEach((placeName, devicesInPlace) -> {
            List<UserPreferences> usersInThisPlace = allUsers.stream()
                    .filter(user -> placeName.equalsIgnoreCase(user.getCurrentPlaceName()))
                    .collect(Collectors.toList());

            System.out.println("Optymalizacja dla miejsca: " + placeName
                    + " | Liczba użytkowników: " + usersInThisPlace.size());

            optimizationService.optimize(devicesInPlace, usersInThisPlace, adminPrefs);
        });

        deviceControlService.applyDeviceSettings(allDevicesFromDb);
    }
}