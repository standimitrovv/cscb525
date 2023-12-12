package com.cscb525.project.model.shipment;

import com.cscb525.project.model.client.Client;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.transportCompany.TransportCompany;
import com.cscb525.project.model.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "departure_point", nullable = false)
    private String departurePoint;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="cargo_weight")
    private double cargoWeight;

    @Column(name="cargo_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoType cargoType;

    @Column(name="payment_status", nullable = false, columnDefinition = "ENUM('PAID', 'NOT_PAID') default 'NOT_PAID'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id", nullable = false)
    private TransportCompany company;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="driver_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;
}
