package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.vehicle.VehicleDto;
import com.cscb525.project.dto.vehicle.VehicleDtoResponse;
import com.cscb525.project.model.vehicle.Vehicle;
import com.cscb525.project.repository.VehicleRepository;
import com.cscb525.project.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<VehicleDtoResponse> getAllVehicles(){
        return this.vehicleRepository
                .findAll()
                .stream()
                .map(this::converToVehicleDtoResponse)
                .collect(Collectors.toList());
    }

    public VehicleDtoResponse getVehicle(int vehicleId){
        Vehicle vehicle = this.vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.converToVehicleDtoResponse(vehicle);
    }

    public VehicleDtoResponse createNewVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDto.getVehicleType());

        return this.converToVehicleDtoResponse(this.vehicleRepository.save(vehicle));
    }

    private VehicleDtoResponse converToVehicleDtoResponse(Vehicle vehicle){
        return this.modelMapper.map(vehicle, VehicleDtoResponse.class);
    }
}
