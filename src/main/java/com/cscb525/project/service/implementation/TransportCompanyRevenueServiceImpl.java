package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.TransportCompanyRevenueDtoResponse;
import com.cscb525.project.model.revenue.TransportCompanyRevenue;
import com.cscb525.project.repository.TransportCompanyRevenueRepository;
import com.cscb525.project.service.TransportCompanyRevenueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportCompanyRevenueServiceImpl implements TransportCompanyRevenueService {
    private final TransportCompanyRevenueRepository revenueRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransportCompanyRevenueServiceImpl(TransportCompanyRevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<TransportCompanyRevenueDtoResponse> getAllRevenues() {
        List<TransportCompanyRevenue> revenues = this.revenueRepository.findAll();

        return revenues
                .stream()
                .map(c -> modelMapper.map(c, TransportCompanyRevenueDtoResponse.class))
                .collect(Collectors.toList());
    }
}
