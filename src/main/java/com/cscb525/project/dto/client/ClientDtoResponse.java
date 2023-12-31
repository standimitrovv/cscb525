package com.cscb525.project.dto.client;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {
    private int id;

    private String name;

    private Set<ShipmentDtoResponse> shipments;
}
