package com.cscb525.project.repository;

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
}
