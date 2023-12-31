package com.cscb525.project.repository;

import com.cscb525.project.model.shipment.Shipment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    // FILTERING

    // Filtering by destination
    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination = :destination")
    List<Shipment> getAllShipmentsWithDestinationEQTo(@Param("destination") String destination);

    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination like :destination%")
    List<Shipment> getAllShipmentsWithDestinationStartingWith(@Param("destination") String destination);

    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination like %:destination")
    List<Shipment> getAllShipmentsWithDestinationEndingWith(@Param("destination") String destination);


    // SORTING

    // Sorting by destination
    @Query(value = "SELECT sh FROM Shipment sh")
    List<Shipment> getAllShipmentsOrderedByDestination(Sort sort);


    // FILTERING & SORTING

    // by destination
    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination = :destination")
    List<Shipment> getAllShipmentsWithDestinationEQToAndDestinationOrdered(@Param("destination") String destination, Sort sort);

    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination like :destination%")
    List<Shipment> getAllShipmentsWithDestinationStartingWithAndDestinationOrdered(@Param("destination") String destination, Sort sort);

    @Query(value = "SELECT sh FROM Shipment sh WHERE sh.destination like %:destination")
    List<Shipment> getAllShipmentsWithDestinationEndingWithAndDestinationOrdered(@Param("destination") String destination, Sort sort);


    // Specific Check-ups
    @Query(value = "SELECT t.name, COUNT(sh) " +
            "FROM Shipment sh " +
            "JOIN FETCH TransportCompany t " +
            "ON t.id = sh.company.id " +
            "GROUP BY t.name")
    List<Object[]> getTotalCompanyShipmentsCount();

    @Query(value = "SELECT t.name, COUNT(sh.id), SUM(sh.price) " +
            "FROM Shipment sh " +
            "JOIN FETCH TransportCompany t " +
            "ON t.id = sh.company.id " +
            "GROUP BY t.name")
    List<Object[]> getTotalCompanyShipmentsCountAndSum();

    @Query(value = "SELECT e.name, COUNT(*) " +
            "FROM Shipment sh " +
            "JOIN FETCH Employee e " +
            "ON sh.employee.id = e.id " +
            "GROUP BY e.name")
    List<Object[]> getEmployeeAndShipmentsCount();

    @Query(value = "SELECT e.name, sh.paymentStatus, SUM(sh.price) " +
            "FROM Shipment sh " +
            "JOIN FETCH Employee e " +
            "ON sh.employee.id = e.id " +
            "GROUP BY e.name, sh.paymentStatus " +
            "HAVING sh.paymentStatus = 'PAID'")
    List<Object[]> getTotalRevenueFromEmployee();
}
