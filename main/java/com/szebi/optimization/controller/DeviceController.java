package com.szebi.optimization.controller;

import com.szebi.optimization.model.entity.Device;
import com.szebi.optimization.model.statusState;
import com.szebi.optimization.service.DeviceControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final DeviceControlService deviceControlService;

    public DeviceController(DeviceControlService deviceControlService) {
        this.deviceControlService = deviceControlService;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceControlService.getAllDevices();
    }

    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceControlService.addDevice(device);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        return deviceControlService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateDeviceStatus(@PathVariable Long id, @RequestParam statusState status) {
        try {
            deviceControlService.changeDeviceStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}