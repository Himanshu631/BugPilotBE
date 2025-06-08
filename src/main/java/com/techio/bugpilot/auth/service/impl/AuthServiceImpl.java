package com.techio.bugpilot.auth.service.impl;

import com.techio.bugpilot.auth.payload.AuthRequest;
import com.techio.bugpilot.auth.payload.AuthResponse;
import com.techio.bugpilot.auth.service.AuthService;
import com.techio.bugpilot.rbac.entity.Permission;
import com.techio.bugpilot.rbac.entity.Role;
import com.techio.bugpilot.rbac.repository.PermissionRepository;
import com.techio.bugpilot.rbac.repository.RoleRepository;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import com.techio.bugpilot.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public GenericResponse<?> authenticate(AuthRequest authRequest) {
        Optional<User> userOptional = userDetailsRepository.findByUsername(authRequest.getUsername());
        if (userOptional.isEmpty()) {
            return GeneralUtility.failure("Invalid username or password");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            return GeneralUtility.failure("Invalid username or password");
        }

        User user = userOptional.get();

        List<String> rolesRes = new ArrayList<>();
        List<String> permissionResp = new ArrayList<>();
        if (user.getRoleIds() != null && !user.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(user.getRoleIds());
            Set<String> permissionIds = roles.stream()
                    .flatMap(role -> role.getPermissionIds().stream())
                    .collect(Collectors.toSet());

            List<Permission> permissions = permissionRepository.findAllById(permissionIds);

            rolesRes = roles.stream().map(Role::getName).toList();
            permissionResp = permissions.stream().map(Permission::getName).toList();
        }


        AuthResponse authResponse = new AuthResponse();
        authResponse.setName(user.getName());
        authResponse.setUserName(user.getUsername());
        authResponse.setAccessToken(jwtUtil.generateToken(authRequest.getUsername(), rolesRes, permissionResp));
        authResponse.setMessage("Login successful");
        authResponse.setRoles(rolesRes);
        authResponse.setAuthorities(permissionResp);

        return GeneralUtility.success("Login successful", authResponse);
    }

}
