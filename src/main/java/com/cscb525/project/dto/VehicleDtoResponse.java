package com.cscb525.project.dto;

import com.cscb525.project.model.VehicleType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDtoResponse {
    @NotBlank
    private Integer id;

    @NotBlank
    private VehicleType vehicleType;
}
