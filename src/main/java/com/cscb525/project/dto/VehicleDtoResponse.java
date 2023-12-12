package com.cscb525.project.dto;

import com.cscb525.project.model.vehicle.VehicleType;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private int id;

    @NotBlank
    private VehicleType vehicleType;

    private Set<ShipmentDtoResponse> shipments;
}
