package com.cscb525.project.controller;

import com.cscb525.project.dto.TransportCompanyRevenueDtoResponse;
import com.cscb525.project.service.implementation.TransportCompanyRevenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/revenue")
public class TransportCompanyRevenueController {
    private final TransportCompanyRevenueServiceImpl revenueService;

    @Autowired
    public TransportCompanyRevenueController(TransportCompanyRevenueServiceImpl revenueService){
        this.revenueService = revenueService;
    }

    @GetMapping
    public List<TransportCompanyRevenueDtoResponse> getAllRevenues(){
        return this.revenueService.getAllRevenues();
    }
}
