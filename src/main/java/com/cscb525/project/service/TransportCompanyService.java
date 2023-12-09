package com.cscb525.project.service;

import com.cscb525.project.dto.*;

import java.util.List;

public interface TransportCompanyService {

    List<TransportCompanyDtoResponse> getAllTransportCompanies();

    TransportCompanyDtoResponse getTransportCompany(Integer companyId);

    TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany);

    TransportCompanyDtoResponse updateTransportCompany(TransportCompanyDto transportCompanyDto, Integer companyId);

    void deleteTransportCompany(Integer companyId);

    // #region COMPANY CLIENT
    TransportCompanyDtoResponse addClient(Integer companyId, Integer clientId);

    void deleteCompanyClient(Integer companyId, Integer clientId);

    TransportCompanyDtoResponse updateCompanyClient(Integer companyId, Integer clientId, ClientDto clientDto);
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    TransportCompanyDtoResponse addCompanyRevenue(Integer companyId,TransportCompanyRevenueDto revenueDto);

    List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(Integer companyId);

    List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(Integer companyId, Integer revenueId, TransportCompanyRevenueDto revenueDto);
    // #endregion COMPANY REVENUE

    TransportCompanyDtoResponse addCompanyVehicle(Integer companyId, Integer vehicleId);
}