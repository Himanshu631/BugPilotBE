package com.techio.bugpilot.client.payload;

import lombok.Data;

@Data
public class ClientResponse {
    private String id;
    private String email;
    private String organization;
}
