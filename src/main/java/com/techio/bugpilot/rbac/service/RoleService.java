package com.techio.bugpilot.rbac.service;

import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.payload.CreateRoleRequest;
import com.techio.bugpilot.utility.GenericResponse;

import java.util.List;

public interface RoleService {
    GenericResponse<Role> createRole(CreateRoleRequest request);
    GenericResponse<Role> addPermissionsToRole(String roleId, List<String> permissionIds);
    GenericResponse<List<Role>> getAllRoles();
}

