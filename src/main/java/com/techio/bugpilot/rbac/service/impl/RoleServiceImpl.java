package com.techio.bugpilot.rbac.service.impl;

import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.payload.CreateRoleRequest;
import com.techio.bugpilot.rbac.repository.PermissionRepository;
import com.techio.bugpilot.rbac.repository.RoleRepository;
import com.techio.bugpilot.rbac.service.RoleService;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import com.techio.bugpilot.utility.AuthContextUtil;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final AuthContextUtil authContextUtil;
    private final UserDetailsRepository userRepository;

    @Override
    public GenericResponse<Role> createRole(CreateRoleRequest request) {
        if (roleRepository.findByName(request.getName()).isEmpty()) {
            return new GenericResponse<>(false, "Role already exists", null);
        }

        Role role = new Role();
        role.setName(request.getName());
        role.setClientId(authContextUtil.getClientId());
        role.setDescription(request.getDescription());
        role.setPermissionIds(request.getPermissionIds());

        Role savedRole = roleRepository.save(role);
        return new GenericResponse<>(true, "Role created successfully", savedRole);
    }

    @Override
    public GenericResponse<Role> addPermissionsToRole(String roleId, List<String> permissionIds) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isEmpty()) {
            return new GenericResponse<>(false, "Role not found", null);
        }

        Role role = optionalRole.get();
        Set<String> existingPermissions = new HashSet<>(role.getPermissionIds());
        existingPermissions.addAll(permissionIds);
        role.setPermissionIds(new ArrayList<>(existingPermissions));
        Role updatedRole = roleRepository.save(role);
        return new GenericResponse<>(true, "Permissions added to role", updatedRole);
    }

    @Override
    public GenericResponse<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return new GenericResponse<>(true, "Fetched roles", roles);
    }

    public GenericResponse<List<Role>> getUserRoles(String userId) {
        String clientId = authContextUtil.getClientId();

        User user = userRepository.findByIdAndClientId(userId, clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Role> roles = roleRepository.findByIdIn(user.getRoleIds());
        return GeneralUtility.success("Roles fetched successfully", roles);
    }

    public GenericResponse<String> assignRoleToUser(String userId, String roleId) {
        String clientId = authContextUtil.getClientId();

        Role role = roleRepository.findByIdAndClientId(roleId, clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found or not allowed"));

        User user = userRepository.findByIdAndClientId(userId, clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getRoleIds() == null) {
            user.setRoleIds(new ArrayList<>());
        }

        if (!user.getRoleIds().contains(roleId)) {
            user.getRoleIds().add(roleId);
            userRepository.save(user);
        }

        return GeneralUtility.success("Role assigned successfully", null);
    }

}

