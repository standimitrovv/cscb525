package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.TransportCompanyDto;
import com.cscb525.project.dto.TransportCompanyDtoResponse;
import com.cscb525.project.model.TransportCompany;
import com.cscb525.project.repository.TransportCompanyRepository;
import com.cscb525.project.service.TransportCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository transportCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransportCompanyServiceImpl(TransportCompanyRepository transportCompanyRepository){
        this.transportCompanyRepository = transportCompanyRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<TransportCompanyDtoResponse> getAllTransportCompanies(){
        List<TransportCompany> transportCompanies = this.transportCompanyRepository.findAll();

        return transportCompanies
                .stream()
                .map(c -> modelMapper.map(c, TransportCompanyDtoResponse.class))
                .collect(Collectors.toList());

    }

    public TransportCompanyDtoResponse getTransportCompany(Integer companyId){
        TransportCompany transportCompany = this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany) {
        TransportCompany transportCompanyToAdd = modelMapper.map(transportCompany, TransportCompany.class);

        transportCompanyToAdd.setName(transportCompany.getName());

        this.transportCompanyRepository.save(transportCompanyToAdd);

        return modelMapper.map(transportCompanyToAdd, TransportCompanyDtoResponse.class);
    }

    public void deleteTransportCompany(Integer companyId) {
        this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.transportCompanyRepository.deleteById(companyId);
    }
}
