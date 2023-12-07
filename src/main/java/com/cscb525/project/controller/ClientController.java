package com.cscb525.project.controller;

import com.cscb525.project.dto.ClientDto;
import com.cscb525.project.dto.ClientDtoResponse;
import com.cscb525.project.service.implementation.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;

    public ClientController(ClientServiceImpl clientServiceImpl){
        this.clientServiceImpl = clientServiceImpl;
    }

    @GetMapping
    public List<ClientDtoResponse> getAllClients() {
        return this.clientServiceImpl.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ClientDtoResponse getClient(@PathVariable Integer clientId){
        return this.clientServiceImpl.getClient(clientId);
    }

    @PostMapping
    public ClientDtoResponse createNewClient(@RequestBody ClientDto clientDto){
        return this.clientServiceImpl.createNewClient(clientDto);
    }

    @PatchMapping("/{clientId}")
    public ClientDtoResponse updateClient(@PathVariable Integer clientId,
                                          @RequestBody ClientDto clientDto) {
        return this.clientServiceImpl.updateClient(clientId, clientDto);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer clientId){
        this.clientServiceImpl.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }
}
