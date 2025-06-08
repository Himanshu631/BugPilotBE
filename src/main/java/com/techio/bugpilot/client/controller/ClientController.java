package com.techio.bugpilot.client.controller;

import com.techio.bugpilot.client.payload.ClientRequest;
import com.techio.bugpilot.client.payload.ClientResponse;
import com.techio.bugpilot.client.service.ClientService;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<GenericResponse<ClientResponse>> createClient(@RequestBody ClientRequest clientRequest) {
        GenericResponse<ClientResponse> genericResponse = clientService.createClient(clientRequest);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<GenericResponse<ClientResponse>> getClient(@PathVariable String clientId) {
        GenericResponse<ClientResponse> genericResponse = clientService.getClientDetails(clientId);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<GenericResponse<?>> getAllClients() {
        GenericResponse<?> genericResponse = clientService.getAllClients();
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<GenericResponse<ClientResponse>> updateClient(@RequestBody ClientRequest request) {
        GenericResponse<ClientResponse> genericResponse = clientService.updateClient(request);
        if (genericResponse.isSuccess()) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}

