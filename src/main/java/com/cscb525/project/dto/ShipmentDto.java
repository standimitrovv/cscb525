package com.cscb525.project.dto;

import com.cscb525.project.model.CargoType;
import com.cscb525.project.model.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank
    private PaymentStatus paymentStatus;
}
