package com.techio.bugpilot.rbac.payload;

import lombok.Data;

import java.util.List;

@Data
public class AssignRoleToUserRequest {
    private String userId;
    private List<String> roleIds;
}
