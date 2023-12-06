package com.cscb525.project.service;

import com.cscb525.project.dto.ClientDto;
import com.cscb525.project.dto.ClientDtoResponse;

import java.util.List;

public interface ClientService {

    List<ClientDtoResponse> getAllClients();

    ClientDtoResponse getClient(Integer clientId);

    ClientDtoResponse createNewClient(ClientDto clientDto);

    ClientDtoResponse updateClient(ClientDto clientDto, Integer clientId);

    void deleteClient(Integer clientId);
}
