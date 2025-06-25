package com.techio.bugpilot.user.service.impl;

import com.techio.bugpilot.config.AppConfig;
import com.techio.bugpilot.rbac.repository.RoleRepository;
import com.techio.bugpilot.user.entity.User;
import com.techio.bugpilot.user.payload.UserRequest;
import com.techio.bugpilot.user.repository.UserDetailsRepository;
import com.techio.bugpilot.user.service.UserService;
import com.techio.bugpilot.utility.AuthContextUtil;
import com.techio.bugpilot.utility.GeneralUtility;
import com.techio.bugpilot.utility.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AuthContextUtil authContextUtil;

    @Autowired
    private RoleRepository roleRepository;

    public GenericResponse<User> createUser(UserRequest userRequest) {
        if (userRequest.getUsername() == null ) {
            return GeneralUtility.failure("Username cannot be null");
        }
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return GeneralUtility.failure("Username already exists");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(appConfig.passwordEncoder().encode(userRequest.getPassword()));
        if (userRequest.getClientId() == null || userRequest.getClientId().isEmpty()) {
            user.setClientId(authContextUtil.getClientId());
        } else {
            user.setClientId(userRequest.getClientId());
        }

        if (userRequest.getIsFromClientAdmin() != null && userRequest.getIsFromClientAdmin().equals(Boolean.TRUE)) {
            roleRepository.findByName("CLIENT_ADMIN").ifPresent(clientAdmin -> user.setRoleIds(List.of(clientAdmin.getId())));
        }

        userRepository.save(user);

        return GeneralUtility.success("User Created Successfully", user);
    }

    @Override
    public GenericResponse<User> assignRoles(String userId, List<String> roleIds) {
        return null;
    }

    @Override
    public GenericResponse<?> getAssignedRoles(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return GeneralUtility.failure("Invalid User Id");
        }

        User user = userOptional.get();

        return GeneralUtility.success("User Details Fetched Successfully", user);
    }

    @Override
    public GenericResponse<?> getAllUsersByClientId() {
        String clientId = authContextUtil.getClientId();
        if (clientId == null || clientId.isEmpty()) {
            return GeneralUtility.failure("Client Id not found");
        }

        List<User> userList = userRepository.findByClientId(clientId);
        if (userList.isEmpty()) {
            return GeneralUtility.failure("No users found for the client");
        }

        return GeneralUtility.success("User List Fetched Successfully", userList);
    }
}
