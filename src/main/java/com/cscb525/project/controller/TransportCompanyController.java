package com.cscb525.project.controller;

import com.cscb525.project.dto.TransportCompanyDto;
import com.cscb525.project.dto.TransportCompanyDtoResponse;
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

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteTransportCompany(@PathVariable Integer companyId) {
        this.transportCompanyServiceImpl.deleteTransportCompany(companyId);
        return ResponseEntity.ok().build();
    }
}
