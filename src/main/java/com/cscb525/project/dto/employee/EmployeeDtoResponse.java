package com.cscb525.project.dto.employee;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.model.employee.DrivingQualification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DrivingQualification drivingQualification;

    private double salary;

    private Set<ShipmentDtoResponse> shipments;
}
