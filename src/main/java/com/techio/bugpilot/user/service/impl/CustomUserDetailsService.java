package com.techio.bugpilot.user.service.impl;

import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.repository.PermissionRepository;
import com.techio.bugpilot.rbac.repository.RoleRepository;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoleIds() != null && !user.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(user.getRoleIds());

            Set<String> permissionIds = roles.stream()
                    .flatMap(role -> role.getPermissionIds().stream())
                    .collect(Collectors.toSet());

            List<Permission> permissions = permissionRepository.findAllById(permissionIds);

            authorities = permissions.stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toList());
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

}
