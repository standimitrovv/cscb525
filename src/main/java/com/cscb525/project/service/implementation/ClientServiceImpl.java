package com.cscb525.project.service.implementation;

import com.cscb525.project.dto.ClientDto;
import com.cscb525.project.dto.ClientDtoResponse;
import com.cscb525.project.model.Client;
import com.cscb525.project.repository.ClientRepository;
import com.cscb525.project.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(c -> modelMapper.map(c, ClientDtoResponse.class))
                .collect(Collectors.toList());
    }

    public ClientDtoResponse getClient(Integer clientId) {
        return modelMapper.map(this.findClientByIdOrThrow(clientId), ClientDtoResponse.class);
    }

    public ClientDtoResponse createNewClient(ClientDto clientDto) {
        Client clientToAdd = modelMapper.map(clientDto, Client.class);

        clientToAdd.setName(clientDto.getName());

        this.clientRepository.save(clientToAdd);

        return modelMapper.map(clientToAdd, ClientDtoResponse.class);
    }

    public ClientDtoResponse updateClient(ClientDto clientDto, Integer clientId){
        Client clientFound = this.findClientByIdOrThrow(clientId);

        clientFound.setName(clientDto.getName());

        Client savedClient = this.clientRepository.save(clientFound);

        return modelMapper.map(savedClient, ClientDtoResponse.class);
    }

    public void deleteClient(Integer clientId){
        Client clientFound = this.findClientByIdOrThrow(clientId);

        this.clientRepository.delete(clientFound);
    }

    private Client findClientByIdOrThrow (Integer clientId) {
        return this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
