package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.shipment.ShipmentDtoResponse;
import com.cscb525.project.model.shipment.*;
import com.cscb525.project.repository.ShipmentRepository;
import com.cscb525.project.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository){
        this.shipmentRepository = shipmentRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ShipmentDtoResponse> getAllShipments(
            SortingAndFilteringCriteria filterBy,
            FilterType filterType,
            SortingAndFilteringCriteria sortBy,
            SortType sortType,
            String destination
    ) {
        Sort.Direction sortDirection = Sort.Direction.ASC;

        if(sortType == SortType.DESC) {
            sortDirection = Sort.Direction.DESC;
        }

        // Filtering AND Sorting at the same time
        if(filterBy != SortingAndFilteringCriteria.NONE && sortBy != SortingAndFilteringCriteria.NONE){
            if(filterBy == SortingAndFilteringCriteria.DESTINATION && sortBy == SortingAndFilteringCriteria.DESTINATION){
                if(destination.isEmpty()){
                    throw new RuntimeException("The 'destination' field must not be empty");
                }

                return switch (filterType){
                    case EQ -> this.convertToShipmentDtoResponseList(
                            this.shipmentRepository.getAllShipmentsWithDestinationEQToAndDestinationOrdered(destination, Sort.by(sortDirection, "destination"))
                    );
                    case STARTS_WITH -> this.convertToShipmentDtoResponseList(
                            this.shipmentRepository.getAllShipmentsWithDestinationStartingWithAndDestinationOrdered(destination, Sort.by(sortDirection, "destination"))
                    );
                    case ENDS_WITH -> this.convertToShipmentDtoResponseList(
                            this.shipmentRepository.getAllShipmentsWithDestinationEndingWithAndDestinationOrdered(destination, Sort.by(sortDirection, "destination"))
                    );
                };
            }
        }

        // Only filtering
        if(filterBy != SortingAndFilteringCriteria.NONE){
            if(destination.isEmpty()){
                throw new RuntimeException("The 'destination' field must not be empty");
            }

            return switch(filterType) {
                case EQ -> this.convertToShipmentDtoResponseList(
                        this.shipmentRepository.getAllShipmentsWithDestinationEQTo(destination)
                );
                case STARTS_WITH -> this.convertToShipmentDtoResponseList(
                        this.shipmentRepository.getAllShipmentsWithDestinationStartingWith(destination)
                );
                case ENDS_WITH -> this.convertToShipmentDtoResponseList(
                        this.shipmentRepository.getAllShipmentsWithDestinationEndingWith(destination)
                );
            };
        }

        // Only sorting
        if(sortBy == SortingAndFilteringCriteria.DESTINATION) {
            return this.convertToShipmentDtoResponseList(
                    this.shipmentRepository.getAllShipmentsOrderedByDestination(Sort.by(sortDirection, "destination"))
            );
        }

        // Neither filtering NOR sorting
        return this.convertToShipmentDtoResponseList(this.shipmentRepository.findAll());
    }


    public List<Object[]> getSpecialCheckUpsByType(CheckUpTypes checkUpType){
        return switch(checkUpType){
                    case TOTAL_SHIPMENTS_COUNT -> this.shipmentRepository.getTotalCompanyShipmentsCount();
                    case TOTAL_SHIPMENTS_COUNT_AND_SUM -> this.shipmentRepository.getTotalCompanyShipmentsCountAndSum();
                    case EMPLOYEE_SHIPMENTS_COUNT -> this.shipmentRepository.getEmployeeAndShipmentsCount();
                    case REVENUE_FROM_EMPLOYEE -> this.shipmentRepository.getTotalRevenueFromEmployee();
        };
    };

    private List<ShipmentDtoResponse> convertToShipmentDtoResponseList(List<Shipment> shipments){
        return shipments
                .stream()
                .map(sh -> this.modelMapper.map(sh, ShipmentDtoResponse.class))
                .collect(Collectors.toList());
    }
}
