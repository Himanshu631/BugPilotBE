package com.techio.bugpilot.user.service;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.repository.PermissionRepository;
import com.techio.bugpilot.rbac.repository.RoleRepository;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserInitializer {

    private final UserDetailsRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initializeAdminUser() {
        return args -> {

            List<String> initialPermissions = List.of(
                    "USER_MANAGE",
                    "ROLE_MANAGE",
                    "PERMISSION_MANAGE"
            );

            List<Permission> savedPermissions = new ArrayList<>();
            for (String perm : initialPermissions) {
                Permission permission = permissionRepository.findByName(perm)
                        .orElseGet(() -> {
                            Permission newPerm = new Permission();
                            newPerm.setName(perm);
                            return permissionRepository.save(newPerm);
                        });
                savedPermissions.add(permission);
            }

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ADMIN");
                        role.setPermissionIds(
                                savedPermissions.stream().map(Permission::getId).toList()
                        );
                        return roleRepository.save(role);
                    });

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setName("Admin User");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoleIds(List.of(adminRole.getId()));
                userRepository.save(admin);
            }
        };
    }
}

