package com.techio.bugpilot.rbac.payload;

import lombok.Data;

import java.util.List;

@Data
public class CreateRoleRequest {
    private String name;
    private String clientId;
    private String description;
    private List<String> permissionIds;
}
