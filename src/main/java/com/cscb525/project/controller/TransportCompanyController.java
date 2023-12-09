package com.cscb525.project.controller;

import com.cscb525.project.dto.*;
import com.cscb525.project.service.implementation.TransportCompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class TransportCompanyController {
    private final TransportCompanyServiceImpl transportCompanyServiceImpl;

    @Autowired
    public TransportCompanyController(TransportCompanyServiceImpl transportCompanyServiceImpl){
        this.transportCompanyServiceImpl = transportCompanyServiceImpl;
    }

    @GetMapping
    public List<TransportCompanyDtoResponse> getAllTransportCompanies(){
        return this.transportCompanyServiceImpl.getAllTransportCompanies();
    }

    @GetMapping("/{companyId}")
    public TransportCompanyDtoResponse getTransportCompany(@PathVariable Integer companyId){
        return this.transportCompanyServiceImpl.getTransportCompany(companyId);
    }

    @PostMapping
    public TransportCompanyDtoResponse addTransportCompany(@RequestBody @Valid TransportCompanyDto transportCompany){
        return this.transportCompanyServiceImpl.addTransportCompany(transportCompany);
    }

    @PatchMapping("/{companyId}")
    public TransportCompanyDtoResponse updateTransportCompany(
            @RequestBody @Valid TransportCompanyDto transportCompanyDto,
            @PathVariable Integer companyId){
        return this.transportCompanyServiceImpl.updateTransportCompany(transportCompanyDto, companyId);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteTransportCompany(@PathVariable Integer companyId) {
        this.transportCompanyServiceImpl.deleteTransportCompany(companyId);
        return ResponseEntity.ok().build();
    }

    // #region COMPANY CLIENT
    @PostMapping("/{companyId}/client/{clientId}")
    public TransportCompanyDtoResponse addClient(@PathVariable Integer companyId, @PathVariable Integer clientId){
        return this.transportCompanyServiceImpl.addClient(companyId, clientId);
    }

    @PatchMapping("/{companyId}/client/{clientId}")
    public TransportCompanyDtoResponse updateCompanyClient(
            @PathVariable Integer companyId,
            @PathVariable Integer clientId,
            @RequestBody ClientDto clientDto){
        return this.transportCompanyServiceImpl.updateCompanyClient(companyId, clientId, clientDto);
    }

    @DeleteMapping("/{companyId}/client/{clientId}")
    public ResponseEntity<Void> deleteCompanyClient(
            @PathVariable Integer companyId,
            @PathVariable Integer clientId
    ){
        this.transportCompanyServiceImpl.deleteCompanyClient(companyId, clientId);
        return ResponseEntity.ok().build();
    }
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    @PostMapping("/{companyId}/revenue")
    public TransportCompanyDtoResponse addCompanyRevenue(
            @PathVariable Integer companyId,
            @RequestBody TransportCompanyRevenueDto revenueDto
    ){
        return this.transportCompanyServiceImpl.addCompanyRevenue(companyId, revenueDto);
    }

    @PatchMapping("/{companyId}/revenue/{revenueId}")
    public List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(
            @PathVariable Integer companyId,
            @PathVariable Integer revenueId,
            @RequestBody TransportCompanyRevenueDto revenueDto
    ) {
        return this.transportCompanyServiceImpl.updateCompanyRevenue(companyId, revenueId, revenueDto);
    }

    @GetMapping("/{companyId}/revenue")
    public List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(
            @PathVariable Integer companyId
    ) {
        return this.transportCompanyServiceImpl.getAllCompanyRevenues(companyId);
    }
    // #endregion COMPANY REVENUE

    // #region COMPANY VEHICLE
    @PostMapping("/{companyId}/vehicle/{vehicleId}")
    public TransportCompanyDtoResponse addCompanyVehicle(
            @PathVariable Integer companyId,
            @PathVariable Integer vehicleId
    ) {
        return this.transportCompanyServiceImpl.addCompanyVehicle(companyId, vehicleId);
    }

    @PatchMapping("/{companyId}/vehicle/{vehicleId}")
    public TransportCompanyDtoResponse updateCompanyVehicle(
            @PathVariable Integer companyId,
            @PathVariable Integer vehicleId,
            @RequestBody VehicleDto vehicleDto
    ) {
        return this.transportCompanyServiceImpl.updateCompanyVehicle(companyId, vehicleId, vehicleDto);
    }

    @DeleteMapping("/{companyId}/vehicle/{vehicleId}")
    public TransportCompanyDtoResponse deleteCompanyVehicle(
            @PathVariable Integer companyId,
            @PathVariable Integer vehicleId
    ) {
        return this.transportCompanyServiceImpl.deleteCompanyVehicle(companyId, vehicleId);
    }
    // #endregion COMPANY VEHICLE
}
