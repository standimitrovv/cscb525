package com.cscb525.project.dto.vehicle;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.model.vehicle.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDtoResponse {
    private int id;

    private VehicleType vehicleType;

    private Set<ShipmentDtoResponse> shipments;
}
