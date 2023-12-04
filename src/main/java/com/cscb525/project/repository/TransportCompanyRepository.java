package com.cscb525.project.repository;

import com.cscb525.project.model.TransportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Integer> {

}
