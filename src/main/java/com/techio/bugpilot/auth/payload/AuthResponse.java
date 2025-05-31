package com.techio.bugpilot.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private String userName;
    private String message;
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}

