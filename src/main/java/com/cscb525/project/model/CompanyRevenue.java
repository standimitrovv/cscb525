package com.cscb525.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

enum Months {
    JAN,
    FEB,
    MAR,
    APR,
    MAY,
    JUN,
    JUL,
    AUG,
    SEPT,
    OCT,
    NOV,
    DEC
}

@Entity
@Table(name="revenues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRevenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="for_month", nullable = false)
    private Months forMonth;

    @Column(nullable = false)
    private double revenue;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_name", referencedColumnName = "company_name")
    private TransportCompany transportCompany;
}


