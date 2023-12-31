package com.cscb525.project.dto.transportCompany;

import com.cscb525.project.dto.client.ClientDtoResponse;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.dto.revenue.TransportCompanyRevenueDtoResponse;
import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.dto.vehicle.VehicleDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompanyDtoResponse {

    private int id;

    private String name;

    private Set<ClientDtoResponse> clients;

    private List<TransportCompanyRevenueDtoResponse> revenues;

    private List<VehicleDtoResponse> vehicles;

    private Set<EmployeeDtoResponse> employees;

    private Set<ShipmentDtoResponse> shipments;
}
