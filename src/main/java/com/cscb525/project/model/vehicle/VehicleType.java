package com.cscb525.project.model.vehicle;

import lombok.Getter;

@Getter
public enum VehicleType {
    BUS("Bus"),
    TRUCK("Truck"),
    CISTERN("Cistern"),
    OTHER("Other");

    private final String vehicleType;

    VehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }
}
