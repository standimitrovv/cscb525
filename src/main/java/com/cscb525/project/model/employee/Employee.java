package com.cscb525.project.model.employee;

import com.cscb525.project.model.shipment.Shipment;
import com.cscb525.project.model.transportCompany.TransportCompany;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="driving_qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private DrivingQualification drivingQualification;

    @Column(name="salary", nullable = false)
    private double salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private TransportCompany company;

    @OneToMany(mappedBy = "employee")
    private Set<Shipment> shipments;
}
