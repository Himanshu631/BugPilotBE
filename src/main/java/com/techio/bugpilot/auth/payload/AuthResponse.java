package com.techio.bugpilot.auth.payload;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String name;
    private String userName;
    private String message;
    private String accessToken;
    private String refreshToken;
    private List<Role> roles;
    private List<Permission> authorities;
    private String tokenType = "Bearer";
}

