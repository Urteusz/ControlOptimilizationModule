package com.szebi.optimization.service;

import com.szebi.optimization.model.*;
import com.szebi.optimization.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceControlService {

    private final DeviceRepository deviceRepository;

    public DeviceControlService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Zwraca wszystkie urządzenia (używane przez Controller)
     */
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    /**
     * Dodaje nowe urządzenie
     */
    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    /**
     * Znajduje urządzenie po ID
     */
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    /**
     * Kluczowa metoda wywoływana przez OptimizationController.
     * Zapisuje stan urządzeń w bazie i symuluje wysłanie sygnału do sprzętu.
     */
    public void applyDeviceSettings(List<Device> devices) {
        for (Device device : devices) {
            // 1. Zapisz nowy stan w bazie danych
            deviceRepository.save(device);

            // 2. Symulacja wysłania sygnału do fizycznego sterownika
            simulateHardwareSignal(device);
        }
    }

    /**
     * Metoda pomocnicza do logowania zmian w konsoli (symulacja IoT)
     */
    private void simulateHardwareSignal(Device device) {
        StringBuilder logMsg = new StringBuilder();
        logMsg.append(">> [SIGNAL SENT] Device: ").append(device.getName())
                .append(" (ID: ").append(device.getId()).append(")");

        // Sprawdzamy typ urządzenia, aby wyświetlić odpowiednie parametry
        // Zakładam istnienie klas dziedziczących z diagramu UML
        if (device instanceof HeatingDevice) {
            logMsg.append(" | SET Temp: ").append(((HeatingDevice) device).getTemperature()).append("°C");
        }
        else if (device instanceof LightingDevice) {
            logMsg.append(" | SET Brightness: ").append(((LightingDevice) device).getBrightness()).append("%");
        }
        else if (device instanceof VentilationDevice) {
            logMsg.append(" | SET AirFlow: ").append(((VentilationDevice) device).getAirFlowRate());
        }
        else {
            // Generyczne urządzenie
            logMsg.append(" | Status: ").append(device.getStatus());
        }

        System.out.println(logMsg.toString());
    }

    /**
     * Ręczna zmiana statusu urządzenia (np. z aplikacji mobilnej)
     */
    public void changeDeviceStatus(Long id, statusState newStatus) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));

        device.setStatus(newStatus);
        deviceRepository.save(device);
        System.out.println("Manual status change for device " + device.getName() + ": " + newStatus);
    }
}