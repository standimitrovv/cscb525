package com.cscb525.project.model.vehicle;

import com.cscb525.project.model.shipment.Shipment;
import com.cscb525.project.model.transportCompany.TransportCompany;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private TransportCompany company;

    @OneToMany(mappedBy = "vehicle")
    private Set<Shipment> shipments;
}
