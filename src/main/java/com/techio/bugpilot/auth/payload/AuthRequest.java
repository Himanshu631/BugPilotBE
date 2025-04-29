package com.techio.bugpilot.auth.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String userName;
    private String password;
}
