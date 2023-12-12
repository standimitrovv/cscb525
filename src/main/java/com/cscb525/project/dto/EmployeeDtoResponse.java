package com.cscb525.project.dto;

import com.cscb525.project.model.employee.DrivingQualification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class EmployeeDtoResponse {
    @NotBlank
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private DrivingQualification drivingQualification;

    @NotBlank
    private double salary;

    private Set<ShipmentDtoResponse> shipments;
}
