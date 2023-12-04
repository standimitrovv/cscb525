package com.cscb525.project.service;

import com.cscb525.project.dto.TransportCompanyDto;
import com.cscb525.project.dto.TransportCompanyDtoResponse;
import com.cscb525.project.model.TransportCompany;

import java.util.List;

public interface TransportCompanyService {

    List<TransportCompanyDtoResponse> getAllTransportCompanies();

    TransportCompanyDtoResponse getTransportCompany(Integer companyId);

    TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany);

    void deleteTransportCompany(Integer companyId);
}