package com.techio.bugpilot.client.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ClientResponse {
    private String id;
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

