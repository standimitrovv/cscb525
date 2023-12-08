package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.VehicleDtoResponse;
import com.cscb525.project.repository.VehicleRepository;
import com.cscb525.project.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(v -> modelMapper.map(v, VehicleDtoResponse.class))
                .collect(Collectors.toList());
    }
}
