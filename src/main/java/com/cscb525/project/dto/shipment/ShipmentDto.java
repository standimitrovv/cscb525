package com.cscb525.project.dto.shipment;

import com.cscb525.project.model.shipment.CargoType;
import com.cscb525.project.model.shipment.PaymentStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

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
