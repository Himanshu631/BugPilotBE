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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final AuthContextUtil authContextUtil;
    private final UserService userService;

    public GenericResponse<ClientResponse> createClient(ClientRequest request) {
        Client client = Client.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .organization(request.getOrganization())
                .phoneNumber(request.getPhoneNumber())
                .website(request.getWebsite())
                .address(request.getAddress())
                .industry(request.getIndustry())
                .size(request.getSize())
                .timezone(request.getTimezone())
                .locale(request.getLocale())
                .metadata(request.getMetadata())
                .createdBy(authContextUtil.getUserId())
                .updatedBy(authContextUtil.getUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Create admin user and associate
        UserRequest userRequest = request.getUserRequest();
        userRequest.setClientId(client.getId());
        userRequest.setIsFromClientAdmin(true);
        GenericResponse<User> serviceUser = userService.createUser(userRequest);

        if (!serviceUser.isSuccess()) {
            return GeneralUtility.failure("Admin user could not be created");
        }

        client.setAdminUserId(serviceUser.getData().getId());

        Client savedClient = clientRepository.save(client);
        return GeneralUtility.success("Client created successfully", convertEntityToResponseDto(savedClient));
    }

    public GenericResponse<ClientResponse> getClientDetails(String clientId) {
        return clientRepository.findById(clientId)
                .map(client -> GeneralUtility.success("Client found", convertEntityToResponseDto(client)))
                .orElseGet(() -> GeneralUtility.failure("Client not found"));
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
        client.setPhoneNumber(request.getPhoneNumber());
        client.setWebsite(request.getWebsite());
        client.setAddress(request.getAddress());
        client.setIndustry(request.getIndustry());
        client.setSize(request.getSize());
        client.setTimezone(request.getTimezone());
        client.setLocale(request.getLocale());
        client.setMetadata(request.getMetadata());
        client.setUpdatedAt(LocalDateTime.now());

        Client updatedClient = clientRepository.save(client);
        return GeneralUtility.success("Client updated", convertEntityToResponseDto(updatedClient));
    }

    private ClientResponse convertEntityToResponseDto(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .email(client.getEmail())
                .organization(client.getOrganization())
                .phoneNumber(client.getPhoneNumber())
                .website(client.getWebsite())
                .address(client.getAddress())
                .industry(client.getIndustry())
                .size(client.getSize())
                .timezone(client.getTimezone())
                .locale(client.getLocale())
                .adminUserId(client.getAdminUserId())
                .metadata(client.getMetadata())
                .createdBy(client.getCreatedBy())
                .updatedBy(client.getUpdatedBy())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }
}
