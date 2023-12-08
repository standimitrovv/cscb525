package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.*;
import com.cscb525.project.model.Client;
import com.cscb525.project.model.TransportCompany;
import com.cscb525.project.model.TransportCompanyRevenue;
import com.cscb525.project.repository.ClientRepository;
import com.cscb525.project.repository.TransportCompanyRepository;
import com.cscb525.project.repository.TransportCompanyRevenueRepository;
import com.cscb525.project.service.TransportCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransportCompanyServiceImpl implements TransportCompanyService {

    private final TransportCompanyRepository transportCompanyRepository;

    private final ClientRepository clientRepository;

    private final TransportCompanyRevenueRepository transportCompanyRevenueRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TransportCompanyServiceImpl(
            TransportCompanyRepository transportCompanyRepository,
            ClientRepository clientRepository,
            TransportCompanyRevenueRepository transportCompanyRevenueRepository
    ){
        this.transportCompanyRepository = transportCompanyRepository;
        this.clientRepository = clientRepository;
        this.transportCompanyRevenueRepository = transportCompanyRevenueRepository;
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
        TransportCompany transportCompany = this.findTransportCompanyByIdOrThrow(companyId);

        return modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse addTransportCompany(TransportCompanyDto transportCompany) {
        TransportCompany transportCompanyToAdd = modelMapper.map(transportCompany, TransportCompany.class);

        transportCompanyToAdd.setName(transportCompany.getName());

        this.transportCompanyRepository.save(transportCompanyToAdd);

        return modelMapper.map(transportCompanyToAdd, TransportCompanyDtoResponse.class);
    }

    public TransportCompanyDtoResponse updateTransportCompany(TransportCompanyDto transportCompanyDto, Integer companyId){
        TransportCompany transportCompany = this.findTransportCompanyByIdOrThrow(companyId);

        transportCompany.setName(transportCompanyDto.getName());

        TransportCompany newTransportCompany = this.transportCompanyRepository.save(transportCompany);

        return modelMapper.map(newTransportCompany, TransportCompanyDtoResponse.class);
    }

    public void deleteTransportCompany(Integer companyId) {
        this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.transportCompanyRepository.deleteById(companyId);
    }

    // #region COMPANY CLIENT
    public TransportCompanyDtoResponse addClient(Integer companyId, Integer clientId){
        Client client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        Optional<Client> existingClient = transportCompany.getClients().stream().filter(c -> c.getId() == clientId).findFirst();

        if(existingClient.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        transportCompany.getClients().add(client);

        this.transportCompanyRepository.save(transportCompany);

        return this.modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public void deleteCompanyClient(Integer companyId, Integer clientId) {
        Client client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        transportCompany.getClients().remove(client);

        this.transportCompanyRepository.save(transportCompany);
    };

    public TransportCompanyDtoResponse updateCompanyClient(Integer companyId, Integer clientId, ClientDto clientDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        Client companyClient = transportCompany.getClients().stream()
                .filter(c -> c.getId() == clientId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        companyClient.setName(clientDto.getName());

        return this.modelMapper.map(
                this.transportCompanyRepository.save(transportCompany),
                TransportCompanyDtoResponse.class
        );
    }
    // #endregion COMPANY CLIENT

    // #region COMPANY REVENUE
    public TransportCompanyDtoResponse addCompanyRevenue(Integer companyId, TransportCompanyRevenueDto revenueDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        TransportCompanyRevenue transportCompanyRevenue = this.modelMapper.map(revenueDto, TransportCompanyRevenue.class);

        transportCompanyRevenue.setTransportCompany(transportCompany);
        transportCompanyRevenue.setForMonth(revenueDto.getForMonth());
        transportCompanyRevenue.setRevenue(revenueDto.getRevenue());

        this.transportCompanyRepository.save(transportCompany);
        this.transportCompanyRevenueRepository.save(transportCompanyRevenue);

        return this.modelMapper.map(transportCompany, TransportCompanyDtoResponse.class);
    }

    public List<TransportCompanyRevenueDtoResponse> getAllCompanyRevenues(Integer companyId){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        return transportCompany.getRevenues()
                .stream()
                .map(revenue -> modelMapper.map(revenue, TransportCompanyRevenueDtoResponse.class))
                .collect(Collectors.toList());
    }

    public List<TransportCompanyRevenueDtoResponse> updateCompanyRevenue(Integer companyId, Integer revenueId, TransportCompanyRevenueDto revenueDto){
        TransportCompany transportCompany = findTransportCompanyByIdOrThrow(companyId);

        List<TransportCompanyRevenue> transportCompanyRevenues = transportCompany.getRevenues();

        TransportCompanyRevenue tcr = transportCompanyRevenues
                .stream()
                .filter(r -> r.getId() == revenueId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        tcr.setRevenue(revenueDto.getRevenue());
        tcr.setForMonth(revenueDto.getForMonth());

        this.transportCompanyRepository.save(transportCompany);

        return transportCompanyRevenues
                .stream()
                .map(r -> modelMapper.map(r, TransportCompanyRevenueDtoResponse.class))
                .collect(Collectors.toList());
    }
    // #endregion COMPANY REVENUE

    private TransportCompany findTransportCompanyByIdOrThrow(Integer companyId){
        return this.transportCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }
}
