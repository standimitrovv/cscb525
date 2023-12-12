package com.cscb525.project.model.transportCompany;

import com.cscb525.project.model.client.Client;
import com.cscb525.project.model.employee.Employee;
import com.cscb525.project.model.revenue.TransportCompanyRevenue;
import com.cscb525.project.model.shipment.Shipment;
import com.cscb525.project.model.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="transport_companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "company_name", unique = true)
    private String name;

    @OneToMany(mappedBy = "transportCompany")
    private List<TransportCompanyRevenue> revenues;

    @ManyToMany
    @JoinTable(
            name="company_clients",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "company")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "company")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company")
    private Set<Shipment> shipments;
}
