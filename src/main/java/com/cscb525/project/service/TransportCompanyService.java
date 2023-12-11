package com.cscb525.project.service;

import com.cscb525.project.dto.*;

import java.util.List;

public interface TransportCompanyService {

    List<TransportCompanyDtoResponse> getAllTransportCompanies();

    TransportCompanyDtoResponse getTransportCompany(int companyId);

    TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany);

    TransportCompanyDtoResponse updateTransportCompany(TransportCompanyDto transportCompanyDto, int companyId);

    void deleteTransportCompany(int companyId);

    // #region COMPANY CLIENT
    TransportCompanyDtoResponse addClient(int companyId, int clientId);

    void deleteCompanyClient(int companyId, int clientId);

    TransportCompanyDtoResponse updateCompanyClient(int companyId, int clientId, ClientDto clientDto);
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    TransportCompanyDtoResponse addCompanyRevenue(int companyId, TransportCompanyRevenueDto revenueDto);

    List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(int companyId);

    List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(int companyId, int revenueId, TransportCompanyRevenueDto revenueDto);
    // #endregion COMPANY REVENUE

    // #region COMPANY VEHICLE
    TransportCompanyDtoResponse addCompanyVehicle(int companyId, int vehicleId);

    TransportCompanyDtoResponse updateCompanyVehicle(int companyId, int vehicleId, VehicleDto vehicleDto);

    TransportCompanyDtoResponse deleteCompanyVehicle(int companyId, int vehicleId);
    // #endregion COMPANY VEHICLE

    // #region COMPANY EMPLOYEE
    List<EmployeeDtoResponse> getAllCompanyEmployees(int companyId);

    TransportCompanyDtoResponse addCompanyEmployee(int companyId, int employeeId);

    TransportCompanyDtoResponse updateCompanyEmployee(int companyId, int employeeId, EmployeeDto employeeDto);

    TransportCompanyDtoResponse deleteCompanyEmployee(int companyId, int employeeId);
    // #endregion COMPANY EMPLOYEE
}