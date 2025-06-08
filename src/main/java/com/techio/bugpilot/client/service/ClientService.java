package com.techio.bugpilot.client.service;

import com.techio.bugpilot.client.entity.Client;
import com.techio.bugpilot.client.payload.ClientRequest;
import com.techio.bugpilot.client.payload.ClientResponse;
import com.techio.bugpilot.client.repository.ClientRepository;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.AuthContextUtil;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthContextUtil authContextUtil;

    @Autowired
    private UserService userService;

    public GenericResponse<ClientResponse> createClient(ClientRequest request) {

        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setOrganization(request.getOrganization());

        UserRequest userRequest = request.getUserRequest();
        userRequest.setClientId(client.getId());

        GenericResponse<User> serviceUser = userService.createUser(userRequest);
        if (!serviceUser.isSuccess()) {
            return GeneralUtility.failure("Admin user could not be created");
        }

        clientRepository.save(client);
        return GeneralUtility.success("Client created successfully", convertEntityToResponseDto(client));
    }

    public GenericResponse<ClientResponse> getClientDetails(String clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        return clientOptional.map(client -> GeneralUtility.success("Client found", convertEntityToResponseDto(client))).orElseGet(() -> GeneralUtility.failure("Client not found"));
    }

    public GenericResponse<List<ClientResponse>> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            return GeneralUtility.failure("No clients found");
        }

        List<ClientResponse> responseList = clients.stream()
                .map(this::convertEntityToResponseDto)
                .toList();

        return GeneralUtility.success("Client list fetched successfully", responseList);
    }


    public GenericResponse<ClientResponse> updateClient(ClientRequest request) {
        String clientId = authContextUtil.getClientIdOrThrow();
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isEmpty()) return GeneralUtility.failure("Client not found");

        Client client = optionalClient.get();
        client.setEmail(request.getEmail());
        client.setOrganization(request.getOrganization());

        clientRepository.save(client);
        
        ClientResponse clientResponse = convertEntityToResponseDto(client);
        return GeneralUtility.success("Client updated", clientResponse);
    }

    private ClientResponse convertEntityToResponseDto(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setEmail(client.getEmail());
        response.setOrganization(client.getOrganization());
        return response;
    }

}

