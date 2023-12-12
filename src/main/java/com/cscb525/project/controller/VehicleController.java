package com.cscb525.project.controller;

import com.cscb525.project.dto.vehicle.VehicleDto;
import com.cscb525.project.dto.vehicle.VehicleDtoResponse;
import com.cscb525.project.service.implementation.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleServiceImpl vehicleService;

    @Autowired
    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleDtoResponse> getAllVehicles(){
        return this.vehicleService.getAllVehicles();
    }

    @GetMapping("/{vehicleId}")
    public VehicleDtoResponse getVehicle(@PathVariable Integer vehicleId){
        return this.vehicleService.getVehicle(vehicleId);
    }

    @PostMapping
    public VehicleDtoResponse addVehicle(@RequestBody VehicleDto vehicleDto) {
        return this.vehicleService.addVehicle(vehicleDto);
    }
}
