package com.cscb525.project.service;

import com.cscb525.project.dto.TransportCompanyDto;
import com.cscb525.project.dto.TransportCompanyDtoResponse;
import com.cscb525.project.dto.TransportCompanyRevenueDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TransportCompanyService {

    List<TransportCompanyDtoResponse> getAllTransportCompanies();

    TransportCompanyDtoResponse getTransportCompany(Integer companyId);

    TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany);

    TransportCompanyDtoResponse updateTransportCompany(TransportCompanyDto transportCompanyDto, Integer companyId);

    void deleteTransportCompany(Integer companyId);

    TransportCompanyDtoResponse addClient(Integer companyId, Integer clientId);

    void deleteCompanyClient(Integer companyId, Integer clientId);

    TransportCompanyDtoResponse addCompanyRevenue(Integer companyId,TransportCompanyRevenueDto revenueDto);
}