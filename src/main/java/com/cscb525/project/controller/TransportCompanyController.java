package com.cscb525.project.controller;

import com.cscb525.project.dto.TransportCompanyDto;
import com.cscb525.project.dto.TransportCompanyDtoResponse;
import com.cscb525.project.dto.TransportCompanyRevenueDto;
import com.cscb525.project.dto.TransportCompanyRevenueDtoResponse;
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

    @PutMapping("/{companyId}/client/{clientId}")
    public TransportCompanyDtoResponse addClient(@PathVariable Integer companyId, @PathVariable Integer clientId){
        return this.transportCompanyServiceImpl.addClient(companyId, clientId);
    }

    @DeleteMapping("/{companyId}/client/{clientId}")
    public ResponseEntity<Void> deleteCompanyClient(
            @PathVariable Integer companyId,
            @PathVariable Integer clientId
    ){
        this.transportCompanyServiceImpl.deleteCompanyClient(companyId, clientId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{companyId}/revenue")
    public TransportCompanyDtoResponse addCompanyRevenue(
            @PathVariable Integer companyId,
            @RequestBody TransportCompanyRevenueDto revenueDto
    ){
        return this.transportCompanyServiceImpl.addCompanyRevenue(companyId, revenueDto);
    }

    @GetMapping("/{companyId}/revenue")
    public List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(
            @PathVariable Integer companyId
    ) {
        return this.transportCompanyServiceImpl.getAllCompanyRevenues(companyId);
    }
}
