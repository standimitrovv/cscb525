package com.cscb525.project.service;

import com.cscb525.project.dto.VehicleDto;
import com.cscb525.project.dto.VehicleDtoResponse;

import java.util.List;

public interface VehicleService {

    List<VehicleDtoResponse> getAllVehicles();

    VehicleDtoResponse addVehicle(VehicleDto vehicleDto);
}
