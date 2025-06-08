package com.techio.bugpilot.user.payload;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
public class UserPrincipal implements UserDetails {
    private String userId;
    private String username;
    private String password;
    private String clientId;
    private List<GrantedAuthority> authorities;
}

