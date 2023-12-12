package com.cscb525.project.repository;

import com.cscb525.project.model.vehicle.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE from Vehicle v where v.company.id = ?1")
    void deleteCompany(Integer companyId);
}
