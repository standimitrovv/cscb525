package com.cscb525.project.repository;

import com.cscb525.project.model.common.Months;
import com.cscb525.project.model.transportCompany.TransportCompany;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Integer> {

    // FILTERING
    @Query(value = "SELECT t FROM TransportCompany t WHERE t.name  = ?1")
    List<TransportCompany> getAllTransportCompaniesByName(String name);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue = :value")
    List<TransportCompany> getAllTransportCompaniesWithRevenueEqualTo(@Param("value") double value);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue > :value")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThan(@Param("value") double value);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue >= :value")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanOrEQTo(@Param("value") double value);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue < :value")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThan(@Param("value") double value);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue <= :value")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanOrEQTo(@Param("value") double value);


    // SORTING/ORDERING
    @Query(value = "SELECT t FROM TransportCompany t")
    List<TransportCompany> getAllTransportCompaniesOrderedByName(Sort sort);

    @Query(value = "SELECT t from TransportCompany t JOIN FETCH t.revenues tr ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesOrderedByRevenueASC();

    @Query(value = "SELECT t from TransportCompany t JOIN FETCH t.revenues tr ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesOrderedByRevenueDESC();


    // FILTERING + SORTING/ORDERING

    // FILTER BY NAME
    @Query(value = "SELECT t FROM TransportCompany t WHERE t.name = :name")
    List<TransportCompany> getAllTransportCompaniesByNameAndNameOrdered(@Param("name") String name, Sort sort);

    @Query(value = "SELECT t FROM TransportCompany t JOIN fetch t.revenues tr WHERE t.name = :name ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesByNameAndRevenueOrderedASC(@Param("name") String name);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE t.name = :name ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesByNameAndRevenueOrderedDESC(@Param("name") String name);

    // FILTER BY REVENUE

    // Filter by revenue + Sort by name
    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue = :revenue ORDER BY t.name ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueEqualToAndCompanyNameOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue = :revenue ORDER BY t.name DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueEqualToAndCompanyNameOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue < :revenue ORDER BY t.name ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanAndCompanyNameOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue < :revenue ORDER BY t.name DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanAndCompanyNameOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue <= :revenue ORDER BY t.name ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanOrEQToAndCompanyNameOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue <= :revenue ORDER BY t.name DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanOrEQToAndCompanyNameOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue > :revenue ORDER BY t.name ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanAndCompanyNameOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue > :revenue ORDER BY t.name DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanAndCompanyNameOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue >= :revenue ORDER BY t.name ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanOrEQToAndCompanyNameOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue >= :revenue ORDER BY t.name DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanOrEQToAndCompanyNameOrderedDESC(@Param("revenue") double revenue);

    // Filter by revenue + Sort by revenue
    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue = :revenue ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueEqualToAndRevenueOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue = :revenue ORDER BY tr.revenue DESC ")
    List<TransportCompany> getAllTransportCompaniesWithRevenueEqualToAndRevenueOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue < :revenue ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanAndRevenueOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue < :revenue ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanAndRevenueOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue <= :revenue ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanOrEQToAndRevenueOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue <= :revenue ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueLessThanOrEQToAndRevenueOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue > :revenue ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanAndRevenueOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue > :revenue ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanAndRevenueOrderedDESC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue >= :revenue ORDER BY tr.revenue ASC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanOrEQToAndRevenueOrderedASC(@Param("revenue") double revenue);

    @Query(value = "SELECT t FROM TransportCompany t JOIN FETCH t.revenues tr WHERE tr.revenue >= :revenue ORDER BY tr.revenue DESC")
    List<TransportCompany> getAllTransportCompaniesWithRevenueMoreThanOrEQToAndRevenueOrderedDESC(@Param("revenue") double revenue);


    // Specific Check-ups
    @Query(value = "SELECT c.name, SUM(r.revenue), r.forMonth " +
            "FROM TransportCompany c " +
            "JOIN FETCH TransportCompanyRevenue r " +
            "ON c.id = r.transportCompany.id " +
            "GROUP BY r.forMonth, c.name " +
            "HAVING r.forMonth = :forMonth")
    List<Object[]> getCompanyRevenueForMonth(@Param("forMonth") Months forMonth);
}
