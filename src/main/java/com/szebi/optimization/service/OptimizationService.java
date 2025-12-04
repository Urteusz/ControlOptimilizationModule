package com.szebi.optimization.service;

import com.szebi.optimization.model.entity.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OptimizationService {

    /**
     * Główna metoda optymalizująca urządzenia w jednym konkretnym miejscu
     */
    public void optimize(List<Device> devicesInPlace,
                         List<UserPreferences> usersInPlace,
                         AdministratorPreferences adminPref) {

        // 1. Oblicz średnie preferencje użytkowników (skala 0-5)
        double avgTempPref = calculateAverage(usersInPlace.stream()
                .mapToInt(UserPreferences::getPrefferedTemperature).toArray());

        double avgLightPref = calculateAverage(usersInPlace.stream()
                .mapToInt(UserPreferences::getPrefferedLightining).toArray());

        // 2. Skonwertuj skalę 0-5 na rzeczywiste wartości (np. stopnie Celsjusza)
        double targetTemp = mapScaleToTemperature(avgTempPref, adminPref);
        double targetBrightness = mapScaleToBrightness(avgLightPref);

        // 3. Zastosuj ustawienia do urządzeń
        for (Device device : devicesInPlace) {
            if (device instanceof HeatingDevice) {
                // Ustawiamy temperaturę (uwzględniając limity admina wewnątrz metody mapującej)
                ((HeatingDevice) device).setTemperature(targetTemp);
            }
            else if (device instanceof LightingDevice) {
                // Jasność 0-100%
                ((LightingDevice) device).setBrightness((int) targetBrightness);
            }
            // Tutaj można dodać logikę dla ClimateDevice / VentilationDevice
        }
    }

    // --- Metody pomocnicze ---

    private double calculateAverage(int[] values) {
        if (values.length == 0) return 0.0; // Domyślna wartość, jeśli nikogo nie ma w pokoju
        return java.util.Arrays.stream(values).average().orElse(0.0);
    }

    /**
     * Mapuje skalę 0-5 na stopnie Celsjusza z uwzględnieniem limitów Admina.
     * Np. Skala 0 -> MinTemp, Skala 5 -> MaxTemp
     */
    private double mapScaleToTemperature(double userScaleAvg, AdministratorPreferences adminPref) {
        double min = adminPref.getPrefferedMinTemperature(); // np. 18 stopni
        double max = adminPref.getPrefferedMaxTemperature(); // np. 24 stopnie

        // Jeśli nikogo nie ma (scale = 0), możemy ustawić tryb Eco (min temp)
        if (userScaleAvg <= 0.1) return min;

        // Prosta interpolacja liniowa:
        // Wynik = Min + (Procent_Skali * Zakres)
        // Skala to 0-5, więc dzielimy przez 5.0
        double calculatedTemp = min + ((userScaleAvg / 5.0) * (max - min));

        // Zabezpieczenie (Hard Limit Admina - najważniejsze)
        return Math.max(min, Math.min(max, calculatedTemp));
    }

    /**
     * Mapuje skalę 0-5 na jasność (0-100%)
     */
    private double mapScaleToBrightness(double userScaleAvg) {
        // Skala 0-5 na 0-100%
        // 1 pkt skali = 20% jasności
        return userScaleAvg * 20.0;
    }
}