package com.techio.bugpilot.rbac.service.impl;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.payload.CreatePermissionRequest;
import com.techio.bugpilot.rbac.repository.PermissionRepository;
import com.techio.bugpilot.rbac.service.PermissionService;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public GenericResponse<Permission> createPermission(CreatePermissionRequest request) {
        if (permissionRepository.findByName(request.getName()).isEmpty()) {
            return new GenericResponse<>(false, "Permission already exists", null);
        }

        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setClientId(request.getClientId());
        permission.setDescription(request.getDescription());

        Permission saved = permissionRepository.save(permission);
        return new GenericResponse<>(true, "Permission created successfully", saved);
    }

    @Override
    public GenericResponse<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return new GenericResponse<>(true, "Fetched permissions", permissions);
    }
}

