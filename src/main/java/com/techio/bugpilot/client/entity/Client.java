package com.techio.bugpilot.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "clients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private String organization;
    private String phoneNumber;
    private String website;
    private String address;
    private String industry;
    private String size;
    private String timezone;
    private String locale;
    private String adminUserId;
    private Map<String, Object> metadata;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

