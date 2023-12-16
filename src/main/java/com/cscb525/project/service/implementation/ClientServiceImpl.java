package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.client.ClientDto;
import com.cscb525.project.dto.client.ClientDtoResponse;
import com.cscb525.project.exception.client.ClientNotFoundException;
import com.cscb525.project.model.client.Client;
import com.cscb525.project.repository.ClientRepository;
import com.cscb525.project.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.cscb525.project.exception.ExceptionTextMessages.CLIENT_NOT_FOUND;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ClientDtoResponse> getAllClients(){
        List<Client> clients = this.clientRepository.findAll();

        return clients
                .stream()
                .map(this::convertToClientDtoResponse)
                .collect(Collectors.toList());
    }

    public ClientDtoResponse getClient(int clientId) {
        return this.convertToClientDtoResponse(this.findClientByIdOrThrow(clientId));
    }

    public ClientDtoResponse createNewClient(ClientDto clientDto) {
        Client tempClient = new Client();
        tempClient.setName(clientDto.getName());

        return this.convertToClientDtoResponse(this.clientRepository.save(tempClient));
    }

    public ClientDtoResponse updateClient(int clientId, ClientDto clientDto){
        Client clientFound = this.findClientByIdOrThrow(clientId);
        clientFound.setName(clientDto.getName());

        return this.convertToClientDtoResponse(clientFound);
    }

    public void deleteClient(int clientId){
        Client clientFound = this.findClientByIdOrThrow(clientId);

        this.clientRepository.delete(clientFound);
    }

    private Client findClientByIdOrThrow (int clientId) {
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
    }

    private ClientDtoResponse convertToClientDtoResponse(Client client){
        return this.modelMapper.map(client, ClientDtoResponse.class);
    }
}
