package com.cscb525.project.service;

import com.cscb525.project.dto.client.ClientDto;
import com.cscb525.project.dto.client.ClientDtoResponse;

import java.util.List;

public interface ClientService {

    List<ClientDtoResponse> getAllClients();

    ClientDtoResponse getClient(int clientId);

    ClientDtoResponse createNewClient(ClientDto clientDto);

    ClientDtoResponse updateClient(int clientId, ClientDto clientDto);

    void deleteClient(int clientId);
}
