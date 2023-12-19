package com.cscb525.project.service;

import com.cscb525.project.dto.client.ClientDto;
import com.cscb525.project.dto.employee.EmployeeDto;
import com.cscb525.project.dto.employee.EmployeeDtoResponse;
import com.cscb525.project.dto.revenue.TransportCompanyRevenueDto;
import com.cscb525.project.dto.revenue.TransportCompanyRevenueDtoResponse;
import com.cscb525.project.dto.shipment.ShipmentDto;
import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.dto.transportCompany.TransportCompanyDto;
import com.cscb525.project.dto.transportCompany.TransportCompanyDtoResponse;
import com.cscb525.project.dto.vehicle.VehicleDto;
import com.cscb525.project.model.common.Months;
import com.cscb525.project.model.common.SortType;
import com.cscb525.project.model.shipment.PaymentStatus;
import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.model.transportCompany.SortingAndFilteringCriteria;

import java.util.List;
import java.util.Set;

public interface TransportCompanyService {

    List<TransportCompanyDtoResponse> getAllTransportCompanies(SortType sortType,
                                                               SortingAndFilteringCriteria sortBy,
                                                               FilterType filterType,
                                                               SortingAndFilteringCriteria filterBy,
                                                               String companyNameToFilterBy,
                                                               String revenueToFilterBy
    );

    TransportCompanyDtoResponse getTransportCompany(int companyId);

    List<Object[]> getCompanyRevenueForMonth(Months forMonth);

    TransportCompanyDtoResponse createNewTransportCompany(TransportCompanyDto transportCompany);

    TransportCompanyDtoResponse updateTransportCompany(int companyId, TransportCompanyDto transportCompanyDto);

    void deleteTransportCompany(int companyId);

    // #region COMPANY CLIENT
    TransportCompanyDtoResponse addCompanyClient(int companyId, int clientId);

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

    void deleteCompanyVehicle(int companyId, int vehicleId);
    // #endregion COMPANY VEHICLE

    // #region COMPANY EMPLOYEE
    List<EmployeeDtoResponse> getAllCompanyEmployees(int companyId);

    TransportCompanyDtoResponse addCompanyEmployee(int companyId, int employeeId);

    TransportCompanyDtoResponse updateCompanyEmployee(int companyId, int employeeId, EmployeeDto employeeDto);

    void deleteCompanyEmployee(int companyId, int employeeId);
    // #endregion COMPANY EMPLOYEE

    // #region COMPANY SHIPMENT
    Set<ShipmentDtoResponse> getAllCompanyShipments(int companyId);

    TransportCompanyDtoResponse addShipment(int companyId, int employeeId, int clientId, int vehicleId, ShipmentDto shipmentDto);

    TransportCompanyDtoResponse updateShipmentPaymentStatus(int companyId, int employeeId, int clientId, int vehicleId, int shipmentId, PaymentStatus paymentStatus);
    // #endregion COMPANY SHIPMENT
}