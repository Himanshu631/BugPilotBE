package com.techio.bugpilot.rbac.service;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.payload.CreatePermissionRequest;
import com.techio.bugpilot.utility.GenericResponse;

import java.util.List;

public interface PermissionService {
    GenericResponse<Permission> createPermission(CreatePermissionRequest request);
    GenericResponse<List<Permission>> getAllPermissions();
}

