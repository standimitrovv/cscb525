package com.cscb525.project.model.client;

import com.cscb525.project.model.shipment.Shipment;
import com.cscb525.project.model.transportCompany.TransportCompany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "clients")
    private Set<TransportCompany> companies;

    @OneToMany(mappedBy = "client")
    private Set<Shipment> shipments;
}
