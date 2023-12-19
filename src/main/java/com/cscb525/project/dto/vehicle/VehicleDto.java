package com.cscb525.project.dto.vehicle;

import com.cscb525.project.model.vehicle.VehicleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    @NotNull(message = "The 'vehicleType' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
