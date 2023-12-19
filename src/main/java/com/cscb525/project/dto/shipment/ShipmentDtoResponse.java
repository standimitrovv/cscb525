package com.cscb525.project.dto.shipment;

import com.cscb525.project.model.shipment.CargoType;
import com.cscb525.project.model.shipment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDtoResponse {
    private int id;

    private String departurePoint;

    private String destination;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private double price;

    private double cargoWeight;

    private CargoType cargoType;

    private PaymentStatus paymentStatus;
}
