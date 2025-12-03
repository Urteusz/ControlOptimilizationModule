package com.szebi.optimization.controller;

import com.szebi.optimization.service.DeviceControlService;
import com.szebi.optimization.service.OptimizationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/optimization")
public class OptimizationController {
    private final OptimizationService optimizationService;
    private final DeviceControlService deviceControlService;

    public OptimizationController(OptimizationService optimizationService, DeviceControlService deviceControlService) {
        this.optimizationService = optimizationService;
        this.deviceControlService = deviceControlService;
    }




}
