package com.cscb525.project.controller;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.model.shipment.CheckUpTypes;
import com.cscb525.project.model.shipment.FilterType;
import com.cscb525.project.model.shipment.SortType;
import com.cscb525.project.model.shipment.SortingAndFilteringCriteria;
import com.cscb525.project.service.implementation.ShipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    private final ShipmentServiceImpl shipmentService;

    @Autowired
    public ShipmentController(ShipmentServiceImpl shipmentService){
        this.shipmentService=shipmentService;
    }

    @GetMapping
    public List<ShipmentDtoResponse> getAllShipments(
            @RequestParam(name = "filterBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria filterBy,
            @RequestParam(name = "filterType", required = false, defaultValue = "EQ") FilterType filterType,
            @RequestParam(name = "sortBy", required = false, defaultValue = "NONE") SortingAndFilteringCriteria sortBy,
            @RequestParam(name = "sortType", required = false, defaultValue = "ASC") SortType sortType,
            @RequestParam(name = "destination", required = false, defaultValue = "") String destination
    ){
        return this.shipmentService.getAllShipments(filterBy, filterType, sortBy, sortType, destination);
    }

    @GetMapping("/check-ups")
    public List<Object[]> getSpecialCheckUpsByType(
            @RequestParam(name = "checkUpType", required = false, defaultValue = "TOTAL_SHIPMENTS_COUNT") CheckUpTypes checkUpType
    ){
        return this.shipmentService.getSpecialCheckUpsByType(checkUpType);
    }
}
