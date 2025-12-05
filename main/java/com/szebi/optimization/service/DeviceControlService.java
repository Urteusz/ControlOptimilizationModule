package com.szebi.optimization.service;

import com.szebi.optimization.model.*;
import com.szebi.optimization.model.entity.Device;
import com.szebi.optimization.model.entity.HeatingDevice;
import com.szebi.optimization.model.entity.LightingDevice;
import com.szebi.optimization.model.entity.VentilationDevice;
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

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public void applyDeviceSettings(List<Device> devices) {
        for (Device device : devices) {
            deviceRepository.save(device);

            simulateHardwareSignal(device);
        }
    }


    private void simulateHardwareSignal(Device device) {
        StringBuilder logMsg = new StringBuilder();
        logMsg.append(">> [SIGNAL SENT] Device: ").append(device.getName())
                .append(" (ID: ").append(device.getId()).append(")");

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
            logMsg.append(" | Status: ").append(device.getStatus());
        }

        System.out.println(logMsg.toString());
    }


    public void changeDeviceStatus(Long id, statusState newStatus) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));

        device.setStatus(newStatus);
        deviceRepository.save(device);
        System.out.println("Manual status change for device " + device.getName() + ": " + newStatus);
    }
}