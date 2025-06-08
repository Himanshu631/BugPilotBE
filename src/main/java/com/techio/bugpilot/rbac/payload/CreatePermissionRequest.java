package com.techio.bugpilot.rbac.payload;

import lombok.Data;

@Data
public class CreatePermissionRequest {
    private String name;
    private String clientId;
    private String description;
}
