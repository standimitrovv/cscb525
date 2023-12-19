package com.cscb525.project.controller;

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
import com.cscb525.project.model.revenue.Months;
import com.cscb525.project.model.shipment.PaymentStatus;
import com.cscb525.project.model.transportCompany.FilterType;
import com.cscb525.project.model.transportCompany.SortType;
import com.cscb525.project.model.transportCompany.SortingAndFilteringCriteria;
import com.cscb525.project.service.implementation.TransportCompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/company")
public class TransportCompanyController {
    private final TransportCompanyServiceImpl transportCompanyServiceImpl;

    @Autowired
    public TransportCompanyController(TransportCompanyServiceImpl transportCompanyServiceImpl){
        this.transportCompanyServiceImpl = transportCompanyServiceImpl;
    }

    @GetMapping
    public List<TransportCompanyDtoResponse> getAllTransportCompanies(
            @RequestParam(name = "sortType", required = false, defaultValue = "ASC") SortType sortType,
            @RequestParam(name = "sortBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria sortBy,
            @RequestParam(name = "filterType", required = false, defaultValue = "EQ") FilterType filterType,
            @RequestParam(name = "filterBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria filterBy,
            @RequestParam(name = "revenue", required = false, defaultValue = "") String revenueToFilterBy,
            @RequestParam(name = "companyName", required = false, defaultValue = "") String companyNameToFilterBy
    ){
        return this.transportCompanyServiceImpl.getAllTransportCompanies(sortType, sortBy, filterType, filterBy, companyNameToFilterBy, revenueToFilterBy);
    }

    @GetMapping("/{companyId}")
    public TransportCompanyDtoResponse getTransportCompany(@PathVariable int companyId){
        return this.transportCompanyServiceImpl.getTransportCompany(companyId);
    }

    @GetMapping("/check-ups")
    public List<Object[]> getCompanyRevenueForMonth(
            @RequestParam(name = "forMonth", required = true) Months forMonth
    ){
        return this.transportCompanyServiceImpl.getCompanyRevenueForMonth(forMonth);
    }

    @PostMapping
    public TransportCompanyDtoResponse createNewTransportCompany(@RequestBody TransportCompanyDto transportCompany){
        return this.transportCompanyServiceImpl.createNewTransportCompany(transportCompany);
    }

    @PatchMapping("/{companyId}")
    public TransportCompanyDtoResponse updateTransportCompany(
            @PathVariable int companyId,
            @RequestBody TransportCompanyDto transportCompanyDto
            ){
        return this.transportCompanyServiceImpl.updateTransportCompany(companyId, transportCompanyDto);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteTransportCompany(@PathVariable int companyId) {
        this.transportCompanyServiceImpl.deleteTransportCompany(companyId);
        return ResponseEntity.ok().build();
    }

    // #region COMPANY CLIENT
    @PostMapping("/{companyId}/client/{clientId}")
    public TransportCompanyDtoResponse addCompanyClient(@PathVariable int companyId, @PathVariable int clientId){
        return this.transportCompanyServiceImpl.addCompanyClient(companyId, clientId);
    }

    @PatchMapping("/{companyId}/client/{clientId}")
    public TransportCompanyDtoResponse updateCompanyClient(
            @PathVariable int companyId,
            @PathVariable int clientId,
            @RequestBody ClientDto clientDto){
        return this.transportCompanyServiceImpl.updateCompanyClient(companyId, clientId, clientDto);
    }

    @DeleteMapping("/{companyId}/client/{clientId}")
    public ResponseEntity<Void> deleteCompanyClient(
            @PathVariable int companyId,
            @PathVariable int clientId
    ){
        this.transportCompanyServiceImpl.deleteCompanyClient(companyId, clientId);
        return ResponseEntity.ok().build();
    }
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    @GetMapping("/{companyId}/revenue")
    public List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(
            @PathVariable int companyId
    ) {
        return this.transportCompanyServiceImpl.getAllCompanyRevenues(companyId);
    }

    @PostMapping("/{companyId}/revenue")
    public TransportCompanyDtoResponse addCompanyRevenue(
            @PathVariable int companyId,
            @RequestBody @Valid TransportCompanyRevenueDto revenueDto
    ){
        return this.transportCompanyServiceImpl.addCompanyRevenue(companyId, revenueDto);
    }

    @PatchMapping("/{companyId}/revenue/{revenueId}")
    public List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(
            @PathVariable int companyId,
            @PathVariable int revenueId,
            @RequestBody @Valid TransportCompanyRevenueDto revenueDto
    ) {
        return this.transportCompanyServiceImpl.updateCompanyRevenue(companyId, revenueId, revenueDto);
    }
    // #endregion COMPANY REVENUE

    // #region COMPANY VEHICLE
    @PostMapping("/{companyId}/vehicle/{vehicleId}")
    public TransportCompanyDtoResponse addCompanyVehicle(
            @PathVariable int companyId,
            @PathVariable int vehicleId
    ) {
        return this.transportCompanyServiceImpl.addCompanyVehicle(companyId, vehicleId);
    }

    @PatchMapping("/{companyId}/vehicle/{vehicleId}")
    public TransportCompanyDtoResponse updateCompanyVehicle(
            @PathVariable int companyId,
            @PathVariable int vehicleId,
            @RequestBody VehicleDto vehicleDto
    ) {
        return this.transportCompanyServiceImpl.updateCompanyVehicle(companyId, vehicleId, vehicleDto);
    }

    @DeleteMapping("/{companyId}/vehicle/{vehicleId}")
    public ResponseEntity<Void> deleteCompanyVehicle(
            @PathVariable int companyId,
            @PathVariable int vehicleId
    ) {
        this.transportCompanyServiceImpl.deleteCompanyVehicle(companyId, vehicleId);
        return ResponseEntity.ok().build();
    }
    // #endregion COMPANY VEHICLE

    // #region COMPANY EMPLOYEE
    @GetMapping("/{companyId}/employee")
    public List<EmployeeDtoResponse> getAllCompanyEmployees(@PathVariable int companyId){
        return this.transportCompanyServiceImpl.getAllCompanyEmployees(companyId);
    }

    @PostMapping("/{companyId}/employee/{employeeId}")
    public TransportCompanyDtoResponse addCompanyEmployee(
            @PathVariable int companyId,
            @PathVariable int employeeId
    ) {
        return this.transportCompanyServiceImpl.addCompanyEmployee(companyId, employeeId);
    }

    @PatchMapping("/{companyId}/employee/{employeeId}")
    public TransportCompanyDtoResponse updateCompanyEmployee(
            @PathVariable int companyId,
            @PathVariable int employeeId,
            @RequestBody EmployeeDto employeeDto
    ) {
        return this.transportCompanyServiceImpl.updateCompanyEmployee(companyId, employeeId, employeeDto);
    }

    @DeleteMapping("/{companyId}/employee/{employeeId}")
    public ResponseEntity<Void> deleteCompanyEmployee(
            @PathVariable int companyId,
            @PathVariable int employeeId
    ) {
        this.transportCompanyServiceImpl.deleteCompanyEmployee(companyId, employeeId);
        return ResponseEntity.ok().build();
    }

    // #endregion COMPANY EMPLOYEE

    // #region COMPANY SHIPMENT
    @GetMapping("/{companyId}/shipment")
    public Set<ShipmentDtoResponse> getAllCompanyShipments(
            @PathVariable int companyId
    ){
        return this.transportCompanyServiceImpl.getAllCompanyShipments(companyId);
    }

    @PostMapping("/{companyId}/employee/{employeeId}/client/{clientId}/vehicle/{vehicleId}/shipment")
    public TransportCompanyDtoResponse addShipment(
            @PathVariable int companyId,
            @PathVariable int employeeId,
            @PathVariable int clientId,
            @PathVariable int vehicleId,
            @RequestBody @Valid ShipmentDto shipmentDto
            ) {
        return this.transportCompanyServiceImpl.addShipment(companyId, employeeId, clientId, vehicleId, shipmentDto);
    }

    @PatchMapping("/{companyId}/employee/{employeeId}/client/{clientId}/vehicle/{vehicleId}/shipment/{shipmentId}")
    public TransportCompanyDtoResponse updateShipmentPaymentStatus (
            @PathVariable int companyId,
            @PathVariable int employeeId,
            @PathVariable int clientId,
            @PathVariable int vehicleId,
            @PathVariable int shipmentId,
            @RequestParam(name = "ps") PaymentStatus paymentStatus
    ) {
        return this.transportCompanyServiceImpl.updateShipmentPaymentStatus(companyId, employeeId, clientId, vehicleId, shipmentId, paymentStatus);
    }
    // #endregion COMPANY SHIPMENT
}
