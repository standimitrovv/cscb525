package com.cscb525.project.dto.shipment;

import com.cscb525.project.model.shipment.CargoType;
import com.cscb525.project.model.shipment.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    @NotBlank
    private String departurePoint;

    @NotBlank
    private String destination;

    @NotBlank
    private LocalDate departureDate;

    @NotBlank
    private LocalDate arrivalDate;

    @NotBlank
    private double price;

    private double cargoWeight;

    @NotBlank
    private CargoType cargoType;

    private PaymentStatus paymentStatus;
}
