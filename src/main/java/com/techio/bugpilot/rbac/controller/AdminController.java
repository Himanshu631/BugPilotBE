package com.techio.bugpilot.rbac.controller;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.payload.CreatePermissionRequest;
import com.techio.bugpilot.rbac.payload.CreateRoleRequest;
import com.techio.bugpilot.rbac.service.PermissionService;
import com.techio.bugpilot.rbac.service.RoleService;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;
    private final PermissionService permissionService;
    private final UserService userService;


    @PostMapping("/roles")
    public ResponseEntity<GenericResponse<Role>> createRole(@RequestBody CreateRoleRequest request) {
        return ResponseEntity.ok(roleService.createRole(request));
    }

    @GetMapping("/roles")
    public ResponseEntity<GenericResponse<?>> getRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping("/permissions")
    public ResponseEntity<GenericResponse<Permission>> createPermission(@RequestBody CreatePermissionRequest request) {
        return ResponseEntity.ok(permissionService.createPermission(request));
    }

    @GetMapping("/permissions")
    public ResponseEntity<GenericResponse<?>> getPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @PostMapping("/roles/{roleId}/permissions")
    public ResponseEntity<GenericResponse<Role>> addPermissions(@PathVariable String roleId, @RequestBody List<String> permissionIds) {
        return ResponseEntity.ok(roleService.addPermissionsToRole(roleId, permissionIds));
    }

    @PostMapping("/users/{userId}/roles")
    public ResponseEntity<GenericResponse<User>> assignRoles(@PathVariable String userId, @RequestBody List<String> roleIds) {
        return ResponseEntity.ok(userService.assignRoles(userId, roleIds));
    }

    @GetMapping("/users/{userId}/roles")
    public ResponseEntity<GenericResponse<?>> getAssignedRoles(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getAssignedRoles(userId));
    }
}

