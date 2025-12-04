package com.szebi.optimization.service;

import com.szebi.optimization.model.entity.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OptimizationService {

    public void optimize(List<Device> devicesInPlace,
                         List<UserPreferences> usersInPlace,
                         AdministratorPreferences adminPref) {

        double avgTempPref = calculateAverage(usersInPlace.stream()
                .mapToInt(UserPreferences::getPrefferedTemperature).toArray());

        double avgLightPref = calculateAverage(usersInPlace.stream()
                .mapToInt(UserPreferences::getPrefferedLightining).toArray());

        double targetTemp = mapScaleToTemperature(avgTempPref, adminPref);
        double targetBrightness = mapScaleToBrightness(avgLightPref);

        for (Device device : devicesInPlace) {
            if (device instanceof HeatingDevice) {
                ((HeatingDevice) device).setTemperature(targetTemp);
            }
            else if (device instanceof LightingDevice) {
                ((LightingDevice) device).setBrightness((int) targetBrightness);
            }
        }
    }

    private double calculateAverage(int[] values) {
        if (values.length == 0) return 0.0;
        return java.util.Arrays.stream(values).average().orElse(0.0);
    }

    private double mapScaleToTemperature(double userScaleAvg, AdministratorPreferences adminPref) {
        double min = adminPref.getPrefferedMinTemperature();
        double max = adminPref.getPrefferedMaxTemperature();

        if (userScaleAvg <= 0.1) return min;

        double calculatedTemp = min + ((userScaleAvg / 5.0) * (max - min));

        return Math.max(min, Math.min(max, calculatedTemp));
    }


    private double mapScaleToBrightness(double userScaleAvg) {
        return userScaleAvg * 20.0;
    }
}