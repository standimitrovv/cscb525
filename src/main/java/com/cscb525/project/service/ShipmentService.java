package com.cscb525.project.service;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.model.shipment.FilterType;
import com.cscb525.project.model.shipment.SortType;
import com.cscb525.project.model.shipment.SortingAndFilteringCriteria;

import java.util.List;

public interface ShipmentService {

    List<ShipmentDtoResponse> getAllShipments(SortingAndFilteringCriteria filterBy,
                                              FilterType filterType,
                                              SortingAndFilteringCriteria sortBy,
                                              SortType sortType,
                                              String destination
    );
}
