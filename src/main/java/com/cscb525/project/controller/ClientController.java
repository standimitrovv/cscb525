package com.cscb525.project.controller;

import com.cscb525.project.dto.client.ClientDto;
import com.cscb525.project.dto.client.ClientDtoResponse;
import com.cscb525.project.service.implementation.ClientServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;

    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl){
        this.clientServiceImpl = clientServiceImpl;
    }

    @GetMapping
    public List<ClientDtoResponse> getAllClients() {
        return this.clientServiceImpl.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ClientDtoResponse getClient(@PathVariable int clientId){
        return this.clientServiceImpl.getClient(clientId);
    }

    @PostMapping
    public ClientDtoResponse createNewClient(@RequestBody @Valid ClientDto clientDto){
        return this.clientServiceImpl.createNewClient(clientDto);
    }

    @PatchMapping("/{clientId}")
    public ClientDtoResponse updateClient(@PathVariable int clientId,
                                          @RequestBody @Valid ClientDto clientDto) {
        return this.clientServiceImpl.updateClient(clientId, clientDto);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId){
        this.clientServiceImpl.deleteClient(clientId);
        return ResponseEntity.ok("Client was successfully deleted!");
    }
}
