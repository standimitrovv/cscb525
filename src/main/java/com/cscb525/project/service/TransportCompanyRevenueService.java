package com.cscb525.project.service;


import com.cscb525.project.dto.revenue.TransportCompanyRevenueDtoResponse;

import java.util.List;

public interface TransportCompanyRevenueService {

    List<TransportCompanyRevenueDtoResponse> getAllRevenues();
}
