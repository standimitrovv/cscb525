package com.cscb525.project.service;

import com.cscb525.project.dto.vehicle.VehicleDto;
import com.cscb525.project.dto.vehicle.VehicleDtoResponse;

import java.util.List;

public interface VehicleService {

    List<VehicleDtoResponse> getAllVehicles();

    VehicleDtoResponse getVehicle(Integer vehicleId);

    VehicleDtoResponse addVehicle(VehicleDto vehicleDto);
}
