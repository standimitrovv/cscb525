package com.cscb525.project.repository;

import com.cscb525.project.model.revenue.TransportCompanyRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportCompanyRevenueRepository extends JpaRepository<TransportCompanyRevenue, Integer> {
}
