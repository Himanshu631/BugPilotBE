package com.techio.bugpilot.client.payload;

import com.techio.bugpilot.user.payload.UserRequest;
import lombok.Data;

@Data
public class ClientRequest {
    private String email;
    private String organization;
    private UserRequest userRequest;
}

