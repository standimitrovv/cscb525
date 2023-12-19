package com.cscb525.project.dto.shipment;

import com.cscb525.project.model.shipment.CargoType;
import com.cscb525.project.model.shipment.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "The 'departurePoint' field cannot be blank!")
    @Size(min = 5, max = 50, message = "The 'departurePoint' field has to contain at least 5 and at most 50 characters!")
    private String departurePoint;

    @NotBlank(message = "The 'destination' field cannot be blank!")
    @Size(min = 5, max = 50, message = "The 'destination' field has to contain at least 5 and at most 50 characters!")
    private String destination;

    @NotNull(message = "The 'departureDate' field cannot be null!")
    @PastOrPresent(message = "The 'departureDate' cannot be in the future!")
    private LocalDate departureDate;

    @NotNull(message = "The 'arrivalDate' field cannot be null!")
    @FutureOrPresent(message = "The 'arrivalDate' cannot be in the past!")
    private LocalDate arrivalDate;

    @NotNull(message = "The 'price' field cannot be null!")
    @Positive(message = "The 'price' must be a number bigger than 0")
    @DecimalMin(value = "1.0", message = "The 'price' field has to contain at least 1 digit")
    @DecimalMax(value = "99999.0", message = "The 'revenue' field has to contain at most 5 digits")
    private double price;

    private double cargoWeight;

    @NotNull(message = "The 'cargoType' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private CargoType cargoType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
