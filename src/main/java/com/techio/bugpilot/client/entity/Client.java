package com.techio.bugpilot.client.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "clients")
@Data
public class Client {

    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private String organization;
    private String adminUserId;
}

